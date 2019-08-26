# Dockerfile
# 1st Stage
FROM node:10.16.3 AS builder
RUN mkdir -p /app
WORKDIR /app
COPY client/package.json .
COPY client/yarn.lock .
RUN yarn install --ignore-platform
COPY client .
RUN yarn build

# 2nd Stage
FROM nginx:1.17.1-alpine
COPY --from=builder /app/dist /usr/share/nginx/html
WORKDIR /usr/share/nginx/html
CMD ["nginx", "-g", "daemon off;"]
