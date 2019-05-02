package designpattern.responsiblechain;

public interface StudyPrepareFilter {
  public void doFilter(PreparationList preparationList, FilterChain filterChain);
}
