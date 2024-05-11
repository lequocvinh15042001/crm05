package utils;

public class DateHelper {

//    public String changeFormatDate(String date, String style) {
//        switch (style) {
//            case "/":
//                String[] strings1 = date.split("-");
//                return strings1[2] + "/" + strings1[1] + "/" + strings1[0];
//            case "-":
//                String[] strings2 = date.split("/");
//                return strings2[2] + "-" + strings2[1] + "-" + strings2[0];
//        }
//        return "";
//    }
	public String changeFormatDate(String date, String style) {
        switch (style) {
            case "/":
                // Kiểm tra xem chuỗi ngày tháng có ký tự ngăn cách là '-' hay không
                if (date.contains("-")) {
                    String[] strings1 = date.split("-");
                    // Lấy ra các phần tử ngày, tháng và năm từ chuỗi và xây dựng lại theo định dạng mới
                    return strings1[2].substring(0, 2) + "/" + strings1[1] + "/" + strings1[0];
                }
                break;
            case "-":
            	String[] strings2 = date.split("/");
            	return strings2[2] + "-" + strings2[1] + "-" + strings2[0];
              
        }
        return "";
    }
    
}
