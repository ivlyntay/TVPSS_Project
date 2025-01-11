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
        <!-- Include sidebar -->
      
        <div th:replace="~{school_sidebar :: school_sidebar('crewList')}"></div>
        <main class="content">
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
                    <input type="text" placeholder="Search..." class="header-search-bar">
                </div><br>
                <div class="filter-container">
                    <div class="filter-item">
                        <i class="bi bi-funnel"></i> <!-- Filter Icon -->
                        <span>Filter By</span>
                    </div>
                    <select class="filter-dropdown">
                        <option value="">Role</option>
                        <option value="Reporter">Reporter</option>
                        <option value="Assistant Director">Assistant Director</option>
                        <option value="Camera Operator">Camera Operator</option>
                        <option value="Script Writer">Script Writer</option>
                        <option value="Video Editor">Video Editor</option>
                    </select>
                    <button class="reset-button">
                        <i class="bi bi-arrow-clockwise"></i> Reset Filter
                    </button>
                </div>
            </section>
			
            <!-- Crew List Section -->
            <section class="crew-list">
                <table>
                    <thead>
                        <tr>
                            <th>Crew Name</th>
                            <th>Role</th>
                            <th>Email</th>
                            <th>Contact Number</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <th:block th:each="crew : ${crewList}">
                            <tr>
                                <td th:text="${crew.fullName}"></td>
                                <td th:text="${crew.role}"></td>
                                <td th:text="${crew.email}"></td>
                                <td th:text="${crew.contactNumber}"></td>
                                <td>
                                    <!-- View Button -->
                                    <button class="action-btn" th:onclick="|window.location.href='/TVPSS_Project/school/crew/view/${crew.id}'|">
									    <i class="bi bi-eye"></i> <!-- View icon -->
									</button>

									<button class="action-btn" th:onclick="|window.location.href='/TVPSS_Project/school/crew/edit/${crew.id}'|">
									    <i class="bi bi-pencil-square"></i> <!-- Edit icon -->
									</button>
									<button class="action-btn" th:onclick="|if(confirm('Are you sure you want to delete this crew member?')) window.location.href='/TVPSS_Project/school/crew/delete/{id}(id=${crew.id}'|">
									    <i class="bi bi-trash"></i> <!-- Delete icon -->
									</button>
                                </td>
                            </tr>
                        </th:block>
                    </tbody>
                </table>
                <button type="button" class="btn btn-primary" th:onclick="|window.location.href='/TVPSS_Project/school/crew/add'|">Add Crew</button>
            </section>
        </main>
    </div>
    <script th:src="@{/js/script.js}"></script>
</body>
</html>
