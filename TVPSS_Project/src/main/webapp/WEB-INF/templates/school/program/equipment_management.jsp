<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Equipment Management</title>
    <link th:href="@{/css/equipment.css}" rel="stylesheet" />
    <link th:href="@{/css/progSidebar_header.css}" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <!-- Include Sidebar -->
        <div th:replace="~{school_sidebar :: school_sidebar('equipment_management')}"></div>

        <main class="content">
            <!-- Include Header -->
            <div th:replace="~{header_school :: header}"></div>
			<div class="header-right">
                    <div class="profile">
                        <img th:src="@{/img/profile.png}" alt="Moni Roy" class="profile-image">
                        <div class="header-profile">
                            <span class="profile-name">Moni Roy</span><br>
                            <span class="role">Admin</span>
                        </div>
                    </div>
                </div>
                
             <h2 class="page-title">Equipment Management</h2>
             <button class="back-btn" th:onclick="|window.location.href='@{/ProgramStatus}'|">Back</button>


            <section class="cards-container">
			    <!-- Level 1 Card -->
			    <div class="level-card">
			        <div class="level-label">Level 1</div>
			        <h3 class="level-title">Beginner</h3>
			        <ul class="equipment-list">
			            <li class="equipment-item">TV Program/Show corner/room</li>
			            <li class="equipment-item">Smartphone</li>
			            <li class="equipment-item">External Mic (Lavalier/Clip Mic)</li>
			            <li class="equipment-item">Monopod</li>
			            <li class="equipment-item">Ring Light</li>
			        </ul>
			        <form th:action="@{/equipment/save}" method="post">
			            <input type="hidden" name="equipmentLevel" value="1">
			            <button type="submit" class="select-btn">Select</button>
			        </form>
			    </div>
			
			    <!-- Level 2 Card -->
			    <div class="level-card">
			        <div class="level-label">Level 2</div>
			        <h3 class="level-title">Intermediate</h3>
			        <ul class="equipment-list">
			            <li class="equipment-item">TV Program/Show corner/room</li>
			            <li class="equipment-item">Editing Corner/Room</li>
			            <li class="equipment-item">Webcam</li>
			            <li class="equipment-item">Tripod</li>
			            <li class="equipment-item">External Mic (Lavalier/Clip Mic) / Wireless Mic</li>
			            <li class="equipment-item">Mobile lighting</li>
			            <li class="equipment-item">Mobile Green Screen set</li>
			            <li class="equipment-item">Editing Software (Open Source)</li>
			        </ul>
			        <form th:action="@{/equipment/save}" method="post">
			            <input type="hidden" name="equipmentLevel" value="2">
			            <button type="submit" class="select-btn">Select</button>
			        </form>
			    </div>
			
			    <!-- Level 3 Card -->
			    <div class="level-card">
			        <div class="level-label">Level 3</div>
			        <h3 class="level-title">Advanced</h3>
			        <ul class="equipment-list">
			            <li class="equipment-item">TV Program/Show corner/room</li>
			            <li class="equipment-item">Editing Corner/Room</li>
			            <li class="equipment-item">Camera</li>
			            <li class="equipment-item">Tripod</li>
			            <li class="equipment-item">External Mic (Lavalier/Clip Mic) / Wireless Mic</li>
			            <li class="equipment-item">Mobile lighting (3 point)</li>
			            <li class="equipment-item">Green Screen (permanent)</li>
			            <li class="equipment-item">Editing Software (Pro/paid version)</li>
			        </ul>
			        <form th:action="@{/equipment/save}" method="post">
			            <input type="hidden" name="equipmentLevel" value="3">
			            <button type="submit" class="select-btn">Select</button>
			        </form>
			    </div>
			</section>

        </main>
    </div>

    <!-- Confirmation Modal -->
    <div id="confirmationModal" class="modal">
        <div class="modal-content">
            <h3>Confirm Selection</h3>
            <p>Are you sure you want to select Level <span id="selectedLevel"></span>?</p>
            <div class="modal-buttons">
                <button id="confirmBtn" class="btn btn-success">Confirm</button>
                <button id="cancelBtn" class="btn btn-danger">Cancel</button>
            </div>
        </div>
    </div>

    <script>
    document.addEventListener('DOMContentLoaded', function () {
        const modal = document.getElementById('confirmationModal');
        const confirmBtn = document.getElementById('confirmBtn');
        const cancelBtn = document.getElementById('cancelBtn');
        const selectBtns = document.querySelectorAll('.select-btn');
        const selectedLevelSpan = document.getElementById('selectedLevel');

        // Handle Select button click
        selectBtns.forEach(btn => {
            btn.addEventListener('click', function () {
                const level = btn.getAttribute('data-level');
                selectedLevelSpan.textContent = level;
                modal.style.display = "block";
            });
        });

        // Handle confirm button click
        confirmBtn.addEventListener('click', function () {
            modal.style.display = "none"; // Hide modal
            alert('Level ' + selectedLevelSpan.textContent + ' selected!'); // Replace with actual save logic
        });

        // Handle cancel button click
        cancelBtn.addEventListener('click', function () {
            modal.style.display = "none";
        });

        // Close modal when clicking outside
        window.onclick = function (event) {
            if (event.target === modal) {
                modal.style.display = "none";
            }
        };
    });
    </script>
</body>
</html>
