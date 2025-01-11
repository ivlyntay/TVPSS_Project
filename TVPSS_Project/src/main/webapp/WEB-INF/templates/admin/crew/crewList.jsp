<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crew List</title>
    <link rel="stylesheet" th:href="@{/css/styles.css}" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <!-- Include Sidebar -->
        <div th:replace="~{admin_sidebar :: admin_sidebar('crewList')}"></div>
        <main class="content">
            <!-- Include Header -->
             <header class="header">
                <div class="header-right">
                    <div class="profile">
                        <img th:src="@{/img/profile.png}" alt="Moni Roy" class="profile-image">
                        <div class="header-profile">
                            <span class="profile-name">Moni Roy</span><br>
                            <span class="role">Admin</span>
                        </div>
                    </div>
                </div>
            </header>
            
            <h1>Crew List</h1>
            <!-- Filter Section -->
            <section class="filter-section">
                <div class="search-container">
                    <i class="bi bi-search search-icon"></i> <!-- Search Icon -->
                    <input type="text" placeholder="Search..." class="header-search-bar" th:value="${searchTerm}">
                </div><br>
                <div class="filter-container">
                    <div class="filter-item">
                        <i class="bi bi-funnel"></i> <!-- Filter Icon -->
                        <span>Filter By</span>
                    </div>
                    <select class="filter-dropdown" th:value="${selectedRole}">
                        <option value="">Role</option>
                        <option value="Reporter">Reporter</option>
                        <option value="Assistant Director">Assistant Director</option>
                        <option value="Camera Operator">Camera Operator</option>
                        <option value="Script Writer">Script Writer</option>
                        <option value="Video Editor">Video Editor</option>
                    </select>
                    <select class="filter-dropdown" name="schoolName" th:value="${selectedSchool}">
				        <option value="">All Schools</option>
				        <option th:each="school : ${uniqueSchools}" th:value="${school}" th:text="${school}"></option>
				    </select>
                    <button class="reset-button" th:onclick="|window.location.href='@{/admin/crew/crewList}'|">
                        <i class="bi bi-arrow-clockwise"></i> Reset Filter
                    </button>
                </div>
            </section>

            <!-- Crew List Section -->
            <section class="crew-list">
                <table>
                    <thead>
                        <tr>
                            <th>School Name</th>
                            <th>Crew Name</th>
                            <th>Role</th>
                            <th>Email</th>
                            <th>Contact Number</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr th:each="crew : ${crewList}">
                            <td th:text="${crew.user.schoolName}"></td>
                            <td th:text="${crew.fullName}"></td>
                            <td th:text="${crew.role}"></td>
                            <td th:text="${crew.email}"></td>
                            <td th:text="${crew.contactNumber}"></td>
                            <td>
                                <!-- View Icon -->
                                <button class="action-btn" th:onclick="|window.location.href='@{/admin/viewCrew/{id}(id=${crew.id})}'|">
                                    <i class="bi bi-eye"></i> <!-- View icon -->
                                </button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </section>
        </main>
    </div>

   <script>
   document.addEventListener("DOMContentLoaded", () => {
       const searchInput = document.querySelector(".header-search-bar");
       const roleDropdown = document.querySelector("select.filter-dropdown:nth-of-type(1)");
       const schoolDropdown = document.querySelector("select.filter-dropdown:nth-of-type(2)");
       const resetButton = document.querySelector(".reset-button");
       const tableRows = document.querySelectorAll(".crew-list tbody tr");

       // Filter Rows
       function filterRows() {
           const searchTerm = searchInput.value.toLowerCase();
           const selectedRole = roleDropdown.value.toLowerCase();
           const selectedSchool = schoolDropdown.value.toLowerCase();

           tableRows.forEach(row => {
               const schoolName = row.querySelector("td:nth-child(1)").textContent.toLowerCase();
               const crewName = row.querySelector("td:nth-child(2)").textContent.toLowerCase();
               const role = row.querySelector("td:nth-child(3)").textContent.toLowerCase();

               const matchesSearch = crewName.includes(searchTerm);
               const matchesRole = !selectedRole || role === selectedRole;
               const matchesSchool = !selectedSchool || schoolName === selectedSchool;

               row.style.display = matchesSearch && matchesRole && matchesSchool ? "" : "none";
           });
       }

       // Event Listeners
       searchInput.addEventListener("input", filterRows);
       roleDropdown.addEventListener("change", filterRows);
       schoolDropdown.addEventListener("change", filterRows);
       resetButton.addEventListener("click", () => {
           searchInput.value = "";
           roleDropdown.value = "";
           schoolDropdown.value = "";
           filterRows();
       });
   });
   </script>
</body>
</html>
