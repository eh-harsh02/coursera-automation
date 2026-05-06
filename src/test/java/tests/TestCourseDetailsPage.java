package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.CourseDetailsPage;


public class TestCourseDetailsPage extends BaseTest {

    private CourseDetailsPage courseDetailsPage;

    @BeforeClass
    public void init() {
        courseDetailsPage = new CourseDetailsPage(getDriver());
    }

    // ================= TEST 1 =================
    @Test(priority = 9)
    public void validateFirstCourseWindow() {

        courseDetailsPage.openCourse(1);
        String parent = courseDetailsPage.switchToCourseWindow();

        // Assertions
        Assert.assertTrue(courseDetailsPage.isEnrollButtonEnabled(), "Enroll button NOT enabled for Course 1");

        courseDetailsPage.closeCourseWindow(parent);
    }

    // ================= TEST 2 =================
    @Test(priority = 10)
    public void validateSecondCourseWindow() {

        courseDetailsPage.openCourse(2);
        String parent = courseDetailsPage.switchToCourseWindow();

        // Assertions
        Assert.assertTrue(courseDetailsPage.isEnrollButtonEnabled(), "Enroll button NOT enabled for Course 2");

        courseDetailsPage.closeCourseWindow(parent);
    }
}