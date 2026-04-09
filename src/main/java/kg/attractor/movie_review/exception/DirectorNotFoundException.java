package kg.attractor.movie_review.exception;

public class DirectorNotFoundException extends NotFoundEntryException {
    public DirectorNotFoundException() {
        super("Director not found");
    }
}
