#!/usr/bin/env bash
set -euo pipefail
ROOT_DIR="$(cd "$(dirname "$0")" && pwd)"

# usa BuildKit se disponível para melhores caches
export DOCKER_BUILDKIT=1

echo "Construindo imagens (multi-stage) e subindo containers..."
docker compose build --pull
docker compose up -d

echo "Containers up:"
docker compose ps