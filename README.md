# GroupProject_CSW303 - Rental Management System

## 👥 Nhóm phát triển (Team Members)
- **Trọng** (Repository Owner)
- **Đông**
- **Châu**
- **Ánh**

## 📝 Giới thiệu dự án (Project Description)
Đây là dự án Quản lý Thuê Nhà (Rental Management System) thuộc môn học CSW303. 

Hiện tại, hệ thống đã được tái cấu trúc (Refactor) để tuân thủ nghiêm ngặt **Kiến trúc MVC (Model - View - Controller)**:
- **Model:** Quản lý dữ liệu và luật nghiệp vụ (gồm các class `User`, `Tenant`, `Property`, `PropertyOwner`, `RentalContract`). Tuyệt đối không chứa mã giao diện.
- **Controller:** Lớp `RentalController` đóng vai trò trung gian, tiếp nhận yêu cầu từ View và điều phối các phương thức của Model.
- **View:** Lớp `ConsoleView` cung cấp giao diện menu văn bản trực quan để người dùng tương tác, không xử lý trực tiếp logic nghiệp vụ.

## 🚀 Hướng dẫn cài đặt và chạy (How to Run)
1. Clone repository này về máy cục bộ.
2. Mở dự án bằng IDE (VS Code, IntelliJ IDEA, Eclipse, ...).
3. Biên dịch và chạy file `Main.java` để khởi động chương trình.
4. Sử dụng các phím số theo hướng dẫn trên Console Menu để tương tác với hệ thống.