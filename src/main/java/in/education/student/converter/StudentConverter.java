package in.education.student.converter;

import in.education.student.dto.StudentDto;
import in.education.student.model.Batch;
import in.education.student.model.Branch;
import in.education.student.model.Semester;
import in.education.student.model.Student;
import in.education.student.model.Year;
import in.education.student.model.repository.BatchRepository;
import in.education.student.model.repository.BranchRepository;
import in.education.student.model.repository.SemesterRepository;
import in.education.student.model.repository.YearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

@Component
public class StudentConverter {

	private BatchRepository batchRepository;
	private BranchRepository branchRepository;
	private YearRepository yearRepository;
	private SemesterRepository semesterRepository;


	@Autowired
	StudentConverter(
			final BatchRepository batchRepository,
			final BranchRepository branchRepository,
			final YearRepository yearRepository,
			final SemesterRepository semesterRepository) {
		this.batchRepository = batchRepository;
		this.branchRepository = branchRepository;
		this.yearRepository = yearRepository;
		this.semesterRepository = semesterRepository;
	}


	public Student convert(final StudentDto studentDto) {

		Calendar cal = Calendar.getInstance();
		Student student = new Student();
		student.setStudentId(studentDto.getStudentId());
		student.setName(studentDto.getName().trim());
		student.setFatherName(studentDto.getFatherName().trim());
		student.setMotherName(studentDto.getMotherName().trim());

		if(!studentDto.getDob().isEmpty() && !studentDto.getDoj().isEmpty()) {
			try {
				cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(studentDto.getDob().replace("/", "-")));
				student.setDob(cal.getTime());
				cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(studentDto.getDoj().replace("/", "-")));
				student.setDoj(cal.getTime());
			} catch (ParseException e) {

			}
		}

		student.setAadharNo(studentDto.getAadharNo().trim());
		student.setAddress(studentDto.getAddress().trim());
		student.setEmail(studentDto.getEmail().trim());
		student.setParentPhoneNo(studentDto.getParentPhoneNo());
		student.setAlternativePhoneNo(studentDto.getAlternativePhoneNo());
		student.setBloodGroupId(studentDto.getBloodGroupId());
		student.setBatchId(studentDto.getBatchId());
		student.setBranchId(studentDto.getBranchId());
		student.setRollNo(studentDto.getRollNo().trim());
		student.setGender(studentDto.getGender());
		student.setHeight(studentDto.getHeight());
		student.setJoiningYearNo(studentDto.getJoiningYearNo());
		student.setJoiningSemesterId(studentDto.getJoiningSemesterId());

		try {
			if(studentDto.getImage() != null && studentDto.getImage().getBytes().length > 0) {
				student.setPhotoName(studentDto.getImage().getOriginalFilename());
				student.setPhoto(studentDto.getImage().getBytes());

			} else if(studentDto.getPhotoData() != null) {

				student.setPhoto(java.util.Base64.getDecoder().decode(studentDto.getPhotoData()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return student;
	}

	public StudentDto convert(final Student student) {

		StudentDto studentDto = new StudentDto();
		studentDto.setStudentId(student.getStudentId());
		studentDto.setName(student.getName());
		studentDto.setFatherName(student.getFatherName());
		studentDto.setMotherName(student.getMotherName());
		studentDto.setDob(new SimpleDateFormat("dd/MM/yyyy").format(student.getDob()));
		studentDto.setDoj(new SimpleDateFormat("dd/MM/yyyy").format(student.getDoj()));
		studentDto.setAadharNo(student.getAadharNo());
		studentDto.setAddress(student.getAddress());
		studentDto.setEmail(student.getEmail());
		studentDto.setParentPhoneNo(student.getParentPhoneNo());
		studentDto.setAlternativePhoneNo(student.getAlternativePhoneNo());
		studentDto.setBloodGroupId(student.getBloodGroupId());
		studentDto.setBatchId(student.getBatchId());
		studentDto.setBranchId(student.getBranchId());
		studentDto.setRollNo(student.getRollNo());
		studentDto.setGender(student.getGender());
		studentDto.setHeight(student.getHeight());
		studentDto.setJoiningYearNo(student.getJoiningYearNo());
		studentDto.setJoiningSemesterId(student.getJoiningSemesterId());

		if (student.getPhoto() != null && student.getPhoto().length > 0) {
			studentDto.setPhotoData(new String(java.util.Base64.getEncoder().encode(student.getPhoto())));
			studentDto.setPhotoName(student.getPhotoName());

			/*CustomMultipartFile customMultipartFile =
					new CustomMultipartFile(student.getPhoto(), student.getPhotoName());
			try {
				customMultipartFile.transferTo(customMultipartFile.getFile());

			} catch (IllegalStateException | IOException e) {

			}*/
		}

		Optional<Batch> batchOptional  =
				batchRepository.findById(student.getBatchId());
		if(batchOptional.isPresent()) {
			studentDto.setBatch(batchOptional.get().getBatchName());
		}

		Optional<Branch> branchOptional  =
				branchRepository.findById(student.getBranchId());
		if(branchOptional.isPresent()) {
			studentDto.setBranch(branchOptional.get().getBranchName());
		}

		Optional<Year> yearOptional  =
				yearRepository.findById(student.getJoiningYearNo());
		if(yearOptional.isPresent()) {
			studentDto.setYear(yearOptional.get().getYear());
		}

		Optional<Semester> semesterOptional  =
				semesterRepository.findById(student.getJoiningSemesterId());
		if(semesterOptional.isPresent()) {
			studentDto.setSemester(semesterOptional.get().getSemesterName());
		}

		return studentDto;
	}
}

/*class CustomMultipartFile implements MultipartFile {

	private final byte[] fileContent;

	private String fileName;

	private String contentType;

	private File file;

	private String destPath = System.getProperty("java.io.tmpdir");

	private FileOutputStream fileOutputStream;

	public CustomMultipartFile(byte[] fileData, String name) {
		this.fileContent = fileData;
		this.fileName = name;
		file = new File(destPath + fileName);

	}

	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		fileOutputStream = new FileOutputStream(dest);
		fileOutputStream.write(fileContent);
	}

	public void clearOutStreams() throws IOException {
		if (null != fileOutputStream) {
			fileOutputStream.flush();
			fileOutputStream.close();
			file.deleteOnExit();
		}
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public String getOriginalFilename() {
		return null;
	}

	@Override
	public String getContentType() {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public long getSize() {
		return 0;
	}

	@Override
	public byte[] getBytes() throws IOException {
		return fileContent;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(fileContent);
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}*/
