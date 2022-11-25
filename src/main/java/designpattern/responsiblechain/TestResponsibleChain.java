package designpattern.responsiblechain;

import org.junit.Test;

public class TestResponsibleChain {
  @Test
  public void testResponsibilityAdvance() {
    PreparationList preparationList = new PreparationList();
    preparationList.setWashFace(true);
    preparationList.setWashHair(false);
    preparationList.setHaveBreakfast(true);

    Study study = new Study();

    StudyPrepareFilter washFaceFilter = new WashFaceFilter();
    StudyPrepareFilter washHairFilter = new WashHairFilter();
    StudyPrepareFilter haveBreakfastFilter = new HaveBreakfastFilter();

    FilterChain filterChain = new FilterChain(study);
    filterChain.addFilter(washFaceFilter);
    filterChain.addFilter(washHairFilter);
    filterChain.addFilter(haveBreakfastFilter);

    filterChain.doFilter(preparationList, filterChain);
  }
}
