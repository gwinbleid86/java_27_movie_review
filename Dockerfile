FROM openjdk:21-ea-slim
RUN mkdir /app
COPY ./movie_review*.jar ./app/movie_review.jar
WORKDIR /app
#COPY .env ./app/.env
EXPOSE 8089
CMD ["java", "-jar", "movie_review.jar"]
