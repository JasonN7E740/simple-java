#!/bin/bash

IMAGE="docker-registry-idc01-sz.cloudtogo.cn/cloudtogo/devops-thymeleaf:$(date '+%Y%m%d%H%M%S')"
docker build -f Dockerfile  -t "${IMAGE}" .
docker push "${IMAGE}"
