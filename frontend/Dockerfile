# Etapa 1: Build da aplicação Angular
FROM node:24 AS build
WORKDIR /app

COPY package*.json ./
RUN npm install
RUN npm install -g @angular/cli

COPY . .
RUN ng build --configuration production --base-href /financas_pessoais/

# Etapa 2: Servir com Nginx
FROM nginx:alpine
RUN rm /etc/nginx/conf.d/default.conf

# Copia os arquivos estáticos gerados pelo Angular
COPY --from=build /app/dist/frontend/browser /usr/share/nginx/html/financas_pessoais

# Copia a configuração customizada do Nginx
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]