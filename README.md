# Software Engineering Clean Architecture*


## Exemplo com Dockerfile
---

### Construção da Imagem Docker
```bash
docker build -t docker-example -f Dockerfile.example .
```

### Execução da Imagem
```bash
docker run -p 9494:9090 -p 50001:50001 docker-example
```

## Demonstração com Docker Compose

### Execução do Docker Compose (Production)
```bash
docker-compose -f docker-compose.prod.yml up -d
```

### Para execução do Docker Compose
```bash
docker-compose -f docker-compose.prod.yml down
```
