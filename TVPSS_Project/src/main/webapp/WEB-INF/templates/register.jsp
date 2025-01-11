<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
   <title>Create an Account</title>
   <link rel="stylesheet" th:href="@{/css/reg_login.css}">
   <script>
       function toggleFields() {
           const role = document.getElementById('role').value;
           const schoolFields = document.getElementById('schoolFields');
           if (role === 'schoolAdmin') {
               schoolFields.style.display = 'block';
           } else {
               schoolFields.style.display = 'none';
           }
       }
   </script>
   <style>
       body {
           background-color: #4880FF;
           font-family: Arial, sans-serif;
           display: flex;
           justify-content: center;
           align-items: center;
           height: 130vh;
           margin: 0;
           position: relative;
           overflow-y: auto;
       }
       .register-container {
           background-color: white;
           padding: 30px;
           border-radius: 10px;
           box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
           width: 550px;
           text-align: center;
           position: relative;
           z-index: 1;
       }
       #schoolFields {
           display: none;
       }
   </style>
</head>
<body>
   <div class="background"></div>
   <div class="register-container">
       <div class="header">
           <img src="img/logo.png" alt="Logo">
           <h2>TVPSS 7G</h2>
       </div>
       <h2>Create an Account</h2>
       <br>
       <!-- Form submission -->
       <form th:action="@{/register}" method="post" class="register-form">
           <label for="full_name">Full Name</label>
           <input type="text" id="full_name" name="full_name" required>
           <label for="ic_number">IC Number</label>
           <input type="text" id="ic_number" name="ic_number" required>
           <label for="email">Email Address</label>
           <input type="email" id="email" name="email" required>
           <label for="contact_number">Contact Number</label>
           <input type="text" id="contact_number" name="contact_number" required>
           <label for="role">Role</label>
           <select id="role" name="role" required onchange="toggleFields()">
               <option value="">Select Role</option>
               <option value="schoolAdmin">School Admin</option>
               <option value="tvpssAdmin">TVPSS Admin</option>
           </select>
           <div id="schoolFields">
               <label for="school_name">School Name</label>
               <input type="text" id="school_name" name="school_name">
               <label for="district">District</label>
               <select id="district" name="district">
                   <option value="">Select District</option>
                   <option value="Ledang">Ledang</option>
                   <option value="Segamat">Segamat</option>
                   <option value="Muar">Muar</option>
                   <option value="Batu Pahat">Batu Pahat</option>
                   <option value="Kluang">Kluang</option>
                   <option value="Mersing">Mersing</option>
                   <option value="Pontian">Pontian</option>
                   <option value="Kulai Jaya">Kulai Jaya</option>
                   <option value="Kota Tinggi">Kota Tinggi</option>
                   <option value="Johor Bahru">Johor Bahru</option>
               </select>
           </div>
           <label for="password">Password</label>
           <input type="password" id="password" name="password" required>
           <button type="submit">Register</button>
           <div class="login-prompt">
               <span>Already have an account?</span>
               <a th:href="@{/login}">Login</a>
           </div>
       </form>
       <!-- Display success or error messages -->
       <div th:if="${message != null}">
           <p style="color: green;" th:text="${message}"></p>
       </div>
       <div th:if="${error != null}">
           <p style="color: red;" th:text="${error}"></p>
       </div>
   </div>
</body>
</html>
