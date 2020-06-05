FROM openliberty/open-liberty:kernel-java8-openj9-ubi

ARG VERSION=1.0
ARG REVISION=SNAPSHOT

LABEL \
  org.opencontainers.image.authors="Alfredo Cumbe" \
  org.opencontainers.image.vendor="IBM" \
  org.opencontainers.image.url="http://localhost:9080/" \
  org.opencontainers.image.source="" \
  org.opencontainers.image.version="$VERSION" \
  org.opencontainers.image.revision="$REVISION" \
  vendor="Alfred Cumbe" \
  name="challenge" \
  version="$VERSION-$REVISION" \
  summary="Vodacom challenge - System Backend Development " \
  description="System Backend Development "

COPY --chown=1001:0 src/main/liberty/config/ /config/
COPY --chown=1001:0 target/*.war /config/apps/

RUN configure.sh
