<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Management</title>
    <link rel="stylesheet" th:href="@{/css/styles.css}" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <!-- Include Sidebar -->
        <div th:replace="~{admin_sidebar :: admin_sidebar('userList')}"></div>
        <main class="content">
            <!-- Include Header -->
            <header class="header">
                <div class="header-right">
                    <div class="profile">
                        <img th:src="@{/img/profile.png}" alt="Admin Profile" class="profile-image">
                        <div class="header-profile">
                            <span class="profile-name" th:text="${loggedInUser.fullName}"></span><br>
                            <span class="role" th:text="${loggedInUser.role}"></span>
                        </div>
                    </div>
                </div>
            </header>

            <h1>User List</h1>
            <!-- Filter Section -->
            <section class="filter-section">
                <div class="search-container">
                    <i class="bi bi-search search-icon"></i>
                    <input type="text" placeholder="Search..." class="header-search-bar" th:value="${searchTerm}">
                </div><br>
                <div class="filter-container">
                    <div class="filter-item">
                        <i class="bi bi-funnel"></i>
                        <span>Filter By</span>
                    </div>
                    <select class="filter-dropdown" th:value="${selectedDistrict}">
                        <option value="">District</option>
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
                    <button class="reset-button" th:onclick="|window.location.href='@{/admin/user/userList}'|">
                        <i class="bi bi-arrow-clockwise"></i> Reset Filter
                    </button>
                </div>
            </section>
            
            <!-- User List Section -->
            <section class="user-list">
                <table>
                    <thead>
                        <tr>
                            <th>User ID</th>
                            <th>Admin Name</th>
                            <th>School Name</th>
                            <th>District</th>
                            <th>YouTube Channel</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr th:if="${#lists.isEmpty(userList)}">
				        <td colspan="6">No School Admins found.</td>
				    </tr>
				    <tr th:each="user : ${userList}">
				        <td th:text="${user.id}"></td>
				        <td th:text="${user.fullName}"></td>
				        <td th:text="${user.schoolName}"></td>
				        <td th:text="${user.district}"></td>
				        <td th:text="${user.youtubeChannelName}"></td>
				        <td>
				            <!-- View Icon -->
				            <button class="action-btn" th:onclick="|window.location.href='/TVPSS_Project/admin/user/view/${user.id}'|">
				                <i class="bi bi-eye"></i>
				            </button>
				            <!-- Delete Icon -->
				            <button class="action-btn" th:onclick="|if(confirm('Are you sure you want to delete this user?')) window.location.href='@{/admin/user/delete/{id}(id=${user.id})}'|">
				                <i class="bi bi-trash"></i>
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
        const districtDropdown = document.querySelector("select.filter-dropdown");
        const resetButton = document.querySelector(".reset-button");
        const tableRows = document.querySelectorAll(".user-list tbody tr");

        // Filter Rows
        function filterRows() {
            const searchTerm = searchInput.value.toLowerCase();
            const selectedDistrict = districtDropdown.value.toLowerCase();

            tableRows.forEach(row => {
                const schoolName = row.querySelector("td:nth-child(3)").textContent.toLowerCase();
                const district = row.querySelector("td:nth-child(4)").textContent.toLowerCase();
                const adminName = row.querySelector("td:nth-child(2)").textContent.toLowerCase();

                const matchesSearch = adminName.includes(searchTerm) || schoolName.includes(searchTerm);
                const matchesDistrict = !selectedDistrict || district === selectedDistrict;

                row.style.display = matchesSearch && matchesDistrict ? "" : "none";
            });
        }

        // Event Listeners
        searchInput.addEventListener("input", filterRows);
        districtDropdown.addEventListener("change", filterRows);
        resetButton.addEventListener("click", () => {
            searchInput.value = "";
            districtDropdown.value = "";
            filterRows();
        });
    });

    </script>

    <script th:src="@{/js/script.js}"></script>
</body>
</html>
