FROM openjdk:21-ea-slim
COPY ./movie_review*.jar ./movie_review.jar
WORKDIR /app
COPY .env ./.env
EXPOSE 8089
CMD ["java", "-jar", "movie_review.jar"]
