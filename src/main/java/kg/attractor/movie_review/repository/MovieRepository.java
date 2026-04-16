package kg.attractor.movie_review.repository;

import kg.attractor.movie_review.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    //    @Query("select m.id," +
//            "       m.name," +
//            "       m.releaseYear," +
//            "       m.description," +
//            "       m.director " +
//            "from Movie m " +
//            "     inner join MovieCastMember mcm on m.id = mcm.movieCastMemberId.movie.id " +
//            "     inner join CastMember cm on mcm.movieCastMemberId.castMember.id = cm.id " +
//            "where m.releaseYear > :releaseYear " +
//            "  and cm.fullname like '%:castMemberName%'")
    @Query(nativeQuery = true,
            value = "select m.ID," +
                    "       m.NAME," +
                    "       m.RELEASE_YEAR," +
                    "       m.DESCRIPTION," +
                    "       d.DIRECTOR.fullname " +
                    "from MOVIE m " +
                    "   inner join DIRECTOR d on m.DIRECTOR_ID = d.ID " +
                    "   inner join MOVIE_CAST_MEMBER mcm on m.ID = mcm.MOVIE_ID " +
                    "   inner join CAST_MEMBER cm on mcm.CAST_MEMBER_ID = cm.ID " +
                    "where m.RELEASE_YEAR > :releaseYear " +
                    "  and cm.FULLNAME like '%:castMemberName%'")
    List<Movie> findByActorAndReleaseYear(String castMemberName, int releaseYear);
}
