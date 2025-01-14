<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Edit Content</title>
    <link rel="stylesheet" href="../../css/content.css">
    <link rel="stylesheet" href="/TVPSS_Project/css/sidebar_header.css">
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

        .content-form {
            background: white;
            padding: 32px;
            border-radius: 8px;
            box-shadow: 0 1px 3px rgba(0,0,0,0.1);
            max-width: 800px;
            margin: 0 auto;
        }

        .thumbnail-upload {
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 12px;
            margin-bottom: 32px;
            padding: 32px;
            border: 2px dashed #ddd;
            border-radius: 8px;
            cursor: pointer;
        }

        .thumbnail-upload .camera-icon {
            width: 48px;
            height: 48px;
            background: #f8f9fa;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .upload-text {
            color: #4285f4;
            font-size: 14px;
        }

        .form-group {
            margin-bottom: 24px;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            color: #333;
            font-weight: 500;
        }

        .form-control {
            width: 100%;
            padding: 8px 12px;
            border: 1px solid #ddd;
            border-radius: 6px;
            font-size: 14px;
        }

        .form-control:focus {
            outline: none;
            border-color: #4285f4;
        }

        .form-row {
            display: flex;
            gap: 24px;
        }

        .form-row .form-group {
            flex: 1;
        }

        .submit-btn {
            background: #4285f4;
            color: white;
            border: none;
            padding: 12px 24px;
            border-radius: 6px;
            cursor: pointer;
            font-size: 14px;
            width: 100%;
            margin-top: 16px;
        }

        .submit-btn:hover {
            background: #3367d6;
        }
        
        .alert {
            padding: 12px;
            border-radius: 6px;
            margin-bottom: 20px;
        }
        
        .alert-success {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
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
                            <span class="profile-name">Ali bin Abu</span><br>
                            <span class="role">schoolAdmin</span>
                        </div>
                    </div>
                </div>
            </header>
            <h2>Edit Content</h2>
            
            <form class="content-form" action="/TVPSS_Project/school/content/update" method="post" th:object="${content}">
			    <input type="hidden" name="id" th:value="*{id}">
			    
			    <div class="form-group">
			        <label for="videoTitle">Video Name</label>
			        <input type="text" id="videoTitle" name="videoTitle" class="form-control" 
			               th:value="*{videoTitle}" required>
			    </div>
			
			    <div class="form-group">
			        <label for="category">Category</label>
			        <select id="category" name="category" class="form-control" required>
			            <option value="Educational" th:selected="*{category == 'Educational'}">Educational</option>
			            <option value="Awareness" th:selected="*{category == 'Awareness'}">Awareness</option>
			            <option value="Vlog" th:selected="*{category == 'Vlog'}">Vlog</option>
			        </select>
			    </div>
			
			    <div class="form-row">
			        <div class="form-group">
			            <label for="uploadDate">Upload Date</label>
			            <input type="date" id="uploadDate" name="uploadDate" class="form-control" 
			                   th:value="${#dates.format(content.uploadDate, 'yyyy-MM-dd')}" required>
			        </div>
			        <div class="form-group">
			            <label for="videoUrl">Video URL</label>
			            <input type="text" id="videoUrl" name="videoUrl" class="form-control" 
			                   th:value="*{videoUrl}" required>
			        </div>
			    </div>
			
			    <input type="hidden" name="schoolId" th:value="*{school.id}">
			    <button type="submit" class="submit-btn">Update Content</button>
			</form>
        </div>
    </div>
</body>
</html>