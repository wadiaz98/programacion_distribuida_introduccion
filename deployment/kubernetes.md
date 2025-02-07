#Creacion de cluster local

## kind

. kind es una herramienta para ejecutar clústeres de Kubernetes locales utilizando contenedores de Docker como nodos. kind fue principalmente diseñado para pruebas de Kubernetes, pero también puede ser útil para desarrollar aplicaciones locales.

shell
kind create cluster

. Minikube es una herramienta que facilita la ejecución de Kubernetes localmente. Minikube ejecuta un clúster de Kubernetes de un solo nodo dentro de una máquina virtual en su computadora.

shell
minikube start