#for dev
FROM node:18-alpine

WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .

# Exponer el puerto 4200
EXPOSE 4200

# Configurar el comando para que Angular escuche en todas las interfaces
CMD ["npm", "run", "start:docker"]