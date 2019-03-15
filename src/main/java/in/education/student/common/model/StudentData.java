package in.education.student.common.model;

import java.util.Map;

public class StudentData {

	private Map academicYears;
	private Map branches;
	private Map bloodGroupNames;

	public Map getAcademicYears() {
		return academicYears;
	}

	public void setAcademicYears(Map academicYears) {
		this.academicYears = academicYears;
	}

	public Map getBranches() {
		return branches;
	}

	public void setBranches(Map branches) {
		this.branches = branches;
	}

	public Map getBloodGroupNames() {
		return bloodGroupNames;
	}

	public void setBloodGroupNames(Map bloodGroupNames) {
		this.bloodGroupNames = bloodGroupNames;
	}
}
