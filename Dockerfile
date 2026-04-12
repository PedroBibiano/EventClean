FROM ubuntu:latest
LABEL authors="pebibiano"

ENTRYPOINT ["top", "-b"]