package in.education.student.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

public class ExamTypeDto {

	private long examTypeId;
	private String examType;
	private long maxMarks;

	public long getExamTypeId() {
		return examTypeId;
	}

	public void setExamTypeId(long examTypeId) {
		this.examTypeId = examTypeId;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public long getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(long maxMarks) {
		this.maxMarks = maxMarks;
	}
}


