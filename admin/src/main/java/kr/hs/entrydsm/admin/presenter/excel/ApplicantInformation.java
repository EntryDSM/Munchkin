package kr.hs.entrydsm.admin.presenter.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicantInformation {
    private final Workbook workbook = new HSSFWorkbook();
    private final Sheet sheet = workbook.createSheet("applicant information");

    @Value("${munchkin.admission-ticket-path}")
    private String path;

    public Workbook getWorkbook() {
        return workbook;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void format() {
        Row row = sheet.createRow(0);

        row.createCell(0).setCellValue("수험번호");
        row.createCell(1).setCellValue("접수번호");
        row.createCell(2).setCellValue("전형유형");
        row.createCell(3).setCellValue("지역");
        row.createCell(4).setCellValue("추가유형");
        row.createCell(5).setCellValue("성명");
        row.createCell(6).setCellValue("생년월일");
        row.createCell(7).setCellValue("주소");
        row.createCell(8).setCellValue("전화번호");
        row.createCell(9).setCellValue("성별");
        row.createCell(10).setCellValue("학력구분");
        row.createCell(11).setCellValue("졸업년도");
        row.createCell(12).setCellValue("출신학교");
        row.createCell(13).setCellValue("반");
        row.createCell(14).setCellValue("보호자 성명");
        row.createCell(15).setCellValue("보호자 전화번호");

        sheet.setColumnWidth(8, 3000);
        sheet.setColumnWidth(11, 3000);
        sheet.setColumnWidth(12, 4000);
        sheet.setColumnWidth(15, 3000);

        row.createCell(16).setCellValue("국어 1학년 1학기");
        row.createCell(17).setCellValue("국어 1학년 2학기");
        row.createCell(18).setCellValue("국어 2학년 1학기");
        row.createCell(19).setCellValue("국어 2학년 2학기");
        row.createCell(20).setCellValue("국어 3학년 1학기");
        row.createCell(21).setCellValue("국어 3학년 2학기");

        row.createCell(22).setCellValue("사회 1학년 1학기");
        row.createCell(23).setCellValue("사회 1학년 2학기");
        row.createCell(24).setCellValue("사회 2학년 1학기");
        row.createCell(25).setCellValue("사회 2학년 2학기");
        row.createCell(26).setCellValue("사회 3학년 1학기");
        row.createCell(27).setCellValue("사회 3학년 2학기");

        row.createCell(28).setCellValue("역사 1학년 1학기");
        row.createCell(29).setCellValue("역사 1학년 2학기");
        row.createCell(30).setCellValue("역사 2학년 1학기");
        row.createCell(31).setCellValue("역사 2학년 2학기");
        row.createCell(32).setCellValue("역사 3학년 1학기");
        row.createCell(33).setCellValue("역사 3학년 2학기");

        row.createCell(34).setCellValue("수학 1학년 1학기");
        row.createCell(35).setCellValue("수학 1학년 2학기");
        row.createCell(36).setCellValue("수학 2학년 1학기");
        row.createCell(37).setCellValue("수학 2학년 2학기");
        row.createCell(38).setCellValue("수학 3학년 1학기");
        row.createCell(39).setCellValue("수학 3학년 2학기");

        row.createCell(40).setCellValue("과학 1학년 1학기");
        row.createCell(41).setCellValue("과학 1학년 2학기");
        row.createCell(42).setCellValue("과학 2학년 1학기");
        row.createCell(43).setCellValue("과학 2학년 2학기");
        row.createCell(44).setCellValue("과학 3학년 1학기");
        row.createCell(45).setCellValue("과학 3학년 2학기");

        row.createCell(46).setCellValue("기술가정 1학년 1학기");
        row.createCell(47).setCellValue("기술가정 1학년 2학기");
        row.createCell(48).setCellValue("기술가정 2학년 1학기");
        row.createCell(49).setCellValue("기술가정 2학년 2학기");
        row.createCell(50).setCellValue("기술가정 3학년 1학기");
        row.createCell(51).setCellValue("기술가정 3학년 2학기");

        row.createCell(52).setCellValue("영어 1학년 1학기");
        row.createCell(53).setCellValue("영어 1학년 2학기");
        row.createCell(54).setCellValue("영어 2학년 1학기");
        row.createCell(55).setCellValue("영어 2학년 2학기");
        row.createCell(56).setCellValue("영어 3학년 1학기");
        row.createCell(57).setCellValue("영어 3학년 2학기");

        row.createCell(58).setCellValue("1학년 성적 총합");
        row.createCell(59).setCellValue("2학년 성적 총합");
        row.createCell(60).setCellValue("3학년 성적 총합");
        row.createCell(61).setCellValue("교과성적환산점수");

        for (int index = 16 ; index <= 61 ; index++) { sheet.setColumnWidth(index, 4000); }

        row.createCell(62).setCellValue("봉사시간");
        row.createCell(63).setCellValue("봉사점수");

        row.createCell(64).setCellValue("결석");
        row.createCell(65).setCellValue("지각");
        row.createCell(66).setCellValue("조퇴");
        row.createCell(67).setCellValue("결과");
        row.createCell(68).setCellValue("출석점수");

        row.createCell(69).setCellValue("1차전형 총점");

        sheet.setColumnWidth(69, 3000);

        row.createCell(70).setCellValue("자기소개서");
        row.createCell(71).setCellValue("학업계획서");

        sheet.setColumnWidth(70, 10000);
        sheet.setColumnWidth(71, 10000);
    }
}
