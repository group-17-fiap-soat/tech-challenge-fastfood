#!/bin/bash

set -e

# Função para instalar kubectl
install_kubectl() {
  echo "🔧 Instalando kubectl..."
  curl -LO "https://dl.k8s.io/release/$(curl -s https://dl.k8s.io/release/stable.txt)/bin/$(uname | tr '[:upper:]' '[:lower:]')/amd64/kubectl"
  chmod +x kubectl
  sudo mv kubectl /usr/local/bin/
  echo "✅ kubectl instalado com sucesso!"
}

# Função para instalar minikube
install_minikube() {
  echo "🔧 Instalando minikube..."
  curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-$(uname | tr '[:upper:]' '[:lower:]')-amd64
  sudo install minikube-$(uname | tr '[:upper:]' '[:lower:]')-amd64 /usr/local/bin/minikube
  rm minikube-$(uname | tr '[:upper:]' '[:lower:]')-amd64
  echo "✅ minikube instalado com sucesso!"
}

# Verifica kubectl
if ! command -v kubectl &> /dev/null; then
  install_kubectl
else
  echo "✅ kubectl já está instalado."
fi

# Verifica minikube
if ! command -v minikube &> /dev/null; then
  install_minikube
else
  echo "✅ minikube já está instalado."
fi

# Inicia o minikube
echo -e "\n🚀 Iniciando o Minikube..."
minikube start

# Usa o ambiente Docker do Minikube
echo -e "\n🐳 Configurando Docker para usar o ambiente do Minikube..."
eval $(minikube docker-env)

# Build das imagens
echo -e "\n🔨 Build das imagens Docker..."
docker build -f infra/db/Dockerfile -t fastfood-postgres:latest .
docker build -t tech-challenge-fastfood:latest .

# Aplica os manifests do Kubernetes
echo -e "\n📦 Aplicando os YAMLs do diretório ./k8s/"
kubectl apply -f ./k8s/

# Abre o dashboard
echo -e "\n📊 Abrindo o Kubernetes Dashboard..."
minikube dashboard
