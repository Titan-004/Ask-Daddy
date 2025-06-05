FROM ubuntu:latest
LABEL authors="dmish"

ENTRYPOINT ["top", "-b"]