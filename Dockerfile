# Stage 1: Build with Maven
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

WORKDIR /app
COPY pom.xml .
# Leverage Docker cache by downloading dependencies first
RUN mvn dependency:go-offline

COPY src ./src
# Build the application with all optimizations
RUN mvn package -DskipTests \
    -Dmaven.javadoc.skip=true \
    -Dmaven.source.skip=true \
    -Djacoco.skip=true \
    -Dcheckstyle.skip=true \
    -Dfindbugs.skip=true \
    -Dpmd.skip=true \
    -Dspotbugs.skip=true \
    -Dlicense.skip=true

# Stage 2: Runtime image
FROM eclipse-temurin:21-jre-jammy

# Set production-ready JVM options
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0 \
    -XX:+UseContainerSupport \
    -XX:+AlwaysPreTouch \
    -XX:+UseG1GC \
    -XX:MaxGCPauseMillis=200 \
    -XX:InitiatingHeapOccupancyPercent=35 \
    -XX:+HeapDumpOnOutOfMemoryError \
    -XX:HeapDumpPath=/opt/dumps \
    -XX:+ExitOnOutOfMemoryError \
    -Djava.security.egd=file:/dev/./urandom \
    -Dfile.encoding=UTF-8"

# Create user and directories
RUN useradd -m -u 1001 appuser && \
    mkdir -p /opt/app && \
    mkdir -p /opt/dumps && \
    chown appuser:appuser /opt/app /opt/dumps

WORKDIR /opt/app
USER appuser

# Copy the built JAR from builder
COPY --from=builder --chown=appuser:appuser /app/target/github-action-*.jar app.jar

# Health check endpoint (ensure your app has /actuator/health)
HEALTHCHECK --interval=30s --timeout=3s --start-period=10s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

# Best practice - don't run as root
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "exec java ${JAVA_OPTS} -jar app.jar"]