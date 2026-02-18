import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.sql.*;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String shopname = request.getParameter("shopname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // 1️⃣ Check password match
        if (!password.equals(confirmPassword)) {
            out.println("<script>alert('Passwords do not match');location='signup.html';</script>");
            return;
        }

        try {
            Connection con = DBConnection.getConnection();

            // 2️⃣ Check if email already exists
            PreparedStatement check = con.prepareStatement(
                    "SELECT * FROM users WHERE email=?");
            check.setString(1, email);
            ResultSet rs = check.executeQuery();

            if (rs.next()) {
                out.println("<script>alert('Email already registered');location='signup.html';</script>");
                return;
            }

            // 3️⃣ Insert user into database
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO users(username, shopname, email, password) VALUES (?, ?, ?, ?)");

            ps.setString(1, username);
            ps.setString(2, shopname);
            ps.setString(3, email);
            ps.setString(4, password);

            ps.executeUpdate();

            out.println("<script>alert('Signup Successful! Please Login');location='Loginpage.html';</script>");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
