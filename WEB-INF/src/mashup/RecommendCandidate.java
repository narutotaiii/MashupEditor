package mashup;

public class RecommendCandidate implements Comparable {
	String service;
	int score;
	
	RecommendCandidate( String service, int score ) {
		this.service = service;
		this.score = score;
	}

	@Override
	public int compareTo(Object arg0) {
		
		RecommendCandidate rhs = (RecommendCandidate)arg0;
		
		return rhs.score - score;
	}
}
