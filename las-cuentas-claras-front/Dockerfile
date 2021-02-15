# Stage 1
FROM node:lts-alpine3.13 as build-step
RUN mkdir -p /app
WORKDIR /app
COPY package.json /app
RUN npm install
COPY . /app
RUN npm run build --prod

# Stage 2
FROM nginx:1.17
COPY --from=build-step /app/nginx.conf /etc/nginx/nginx.conf
COPY --from=build-step /app/dist/las-cuentas-claras-front /code
EXPOSE 8080:8080
CMD ["nginx", "-g", "daemon off;"]