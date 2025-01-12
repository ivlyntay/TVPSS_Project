<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Content Management</title>
    <link rel="stylesheet" th:href="@{/css/styles.css}" />
    <link rel="stylesheet" href="../../css/content.css">
    <link rel="stylesheet" href="../../css/sidebar_header.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        .container {
            display: flex;
            min-height: 100vh;
        }
        
        .main-content {
            flex: 1;
            background: #f8f9fa;
            padding: 24px;
        }
        
        h2 {
            font-size: 24px;
            color: #333;
            margin-bottom: 24px;
        }
        
        .search-controls {
            display: flex;
            gap: 16px;
            margin-bottom: 24px;
            align-items: center;
        }
        
        .search-input {
            flex: 1;
            max-width: 400px;
            padding: 8px 12px;
            border: 1px solid #ddd;
            border-radius: 6px;
        }
        
        .filter-group {
            display: flex;
            gap: 12px;
            align-items: center;
        }
        
        .filter-btn {
            padding: 8px 16px;
            border: 1px solid #ddd;
            border-radius: 6px;
            background: white;
            cursor: pointer;
        }
        
        .dropdown {
            padding: 8px 16px;
            border: 1px solid #ddd;
            border-radius: 6px;
            background: white;
        }
        
        .reset-filter {
            color: #ff4444;
            text-decoration: none;
        }
        
        .add-new {
            padding: 8px 16px;
            background: #4285f4;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }
        
        .content-table {
            width: 100%;
            background: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 1px 3px rgba(0,0,0,0.1);
        }
        
        .content-table th {
            background: #f8f9fa;
            padding: 12px 16px;
            text-align: left;
            font-weight: 500;
            color: #666;
        }
        
        .content-table td {
            padding: 12px 16px;
            border-top: 1px solid #eee;
        }
        
        .category-tag {
            display: inline-block;
            padding: 4px 12px;
            border-radius: 12px;
            font-size: 12px;
        }
        
        .educational {
            background: #e8f5e9;
            color: #2e7d32;
        }
        
        .awareness {
            background: #e3f2fd;
            color: #1976d2;
        }
        
        .vlog {
            background: #f3e5f5;
            color: #7b1fa2;
        }
        
        .action-cell {
            display: flex;
            gap: 8px;
        }
        
        .action-btn {
            background: none;
            border: none;
            cursor: pointer;
            padding: 4px;
            opacity: 0.7;
        }
        
        .action-btn:hover {
            opacity: 1;
        }
        
        .pagination {
            display: flex;
            justify-content: center;
            gap: 8px;
            margin-top: 24px;
        }
        
        .page-link {
            padding: 8px 12px;
            border: 1px solid #ddd;
            border-radius: 4px;
            color: #666;
            text-decoration: none;
        }
        
        .page-link.active {
            background: #4285f4;
            color: white;
            border-color: #4285f4;
        }
    </style>
</head>
<body>
    <div class="container">
        <div th:replace="~{school_sidebar :: school_sidebar('contentManagement')}"></div>
        <div class="main-content">
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
            
            <h2>Content Management</h2>
            
            <div class="search-controls">
                <input type="text" class="search-input" placeholder="Search">
                
                <div class="filter-group">
                    <button class="filter-btn">Filter By</button>
                    
                    <select class="dropdown">
                        <option>Category</option>
                        <option>Educational</option>
                        <option>Awareness</option>
                        <option>Vlog</option>
                    </select>
                    
                    <select class="dropdown">
                        <option>District</option>
                    </select>
                    
                    <a href="#" class="reset-filter">Reset Filter</a>
                </div>
                
                <a href="/TVPSS_Project/school/content/add" class="add-new" style="display: inline-block; padding: 10px 20px; background: #007bff; color: white; text-decoration: none; border-radius: 5px;">Add New</a>

            </div>

            <table class="content-table">
                <thead>
                    <tr>
                        <th>VIDEO NAME</th>
                        <th>CATEGORY</th>
                        <th>UPLOADED DATE</th>
                        <th>ACTION</th>
                    </tr>
                </thead>
                <tbody>
            <tr th:each="content : ${contentList}">
                <td th:text="${content.videoTitle}">Sample Video Name</td>
                <td>
                    <span class="category-tag" th:classappend="${content.category == 'Educational' ? 'educational' : content.category == 'Awareness' ? 'awareness' : 'vlog'}"
                        th:text="${content.category}">Category</span>
                </td>
                <td th:text="${content.uploadDate}">Oct 15, 2024</td>
                <td>
                    <div class="action-cell">
                        <button class="action-btn" th:onclick="'editContent(' + ${content.id} + ')'">✏️</button>
                        <button class="action-btn" th:onclick="'deleteContent(' + ${content.id} + ')'">🗑️</button>
                    </div>
                </td>
            </tr>
</tbody>
            </table>

            <div class="pagination">
                <a href="#" class="page-link">«</a>
                <a href="#" class="page-link active">1</a>
                <a href="#" class="page-link">2</a>
                <a href="#" class="page-link">3</a>
                <a href="#" class="page-link">»</a>
            </div>
        </div>
    </div>
</body>
</html>