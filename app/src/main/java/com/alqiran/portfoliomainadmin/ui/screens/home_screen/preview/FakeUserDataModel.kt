package com.alqiran.portfoliomainadmin.ui.screens.home_screen.preview

import com.alqiran.portfoliomainadmin.ui.model.ContactAndAccountsUiModel
import com.alqiran.portfoliomainadmin.ui.model.CourseUiModel
import com.alqiran.portfoliomainadmin.ui.model.EducationUiModel
import com.alqiran.portfoliomainadmin.ui.model.ExperienceUiModel
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel
import com.alqiran.portfoliomainadmin.ui.model.SkillUiModel
import com.alqiran.portfoliomainadmin.ui.model.TechnologyTitleUiModel
import com.alqiran.portfoliomainadmin.ui.model.TechnologyUiModel
import com.alqiran.portfoliomainadmin.ui.model.UserUiModel


val project = ProjectUiModel(
    id = 0,
    projectName = "TurboFit",
    description = "TurpoFit is a mobile fitness application built to support users in leading healthier lives by combining modern technology with essential wellness tools. Designed for Android and developed as part of the DEPI graduation project, TurpoFit focuses on creating a personalized and motivating experience for each user.\n" +
            "\n" +
            "The app begins by gathering key health details such as gender, height, weight, fitness level, and personal goals. This information allows TurpoFit to tailor its services and provide relevant insights that match the user’s lifestyle and objectives. Through seamless integration with Health Connect, the app keeps health data synchronized and up to date, giving users a comprehensive view of their physical progress.\n" +
            "\n" +
            "TurpoFit encourages physical activity by offering a selection of guided workouts suitable for various fitness levels. In addition to exercise, it promotes healthy eating habits by allowing users to explore nutritional information, track calorie intake, and even scan meals for automatic calorie estimates. Users can also create and follow healthy recipes, making meal preparation part of their wellness journey.\n" +
            "\n" +
            "To support hydration and overall well-being, TurpoFit includes daily water intake tracking and progress monitoring tools. Whether a user’s goal is weight loss, muscle gain, or general fitness, the app is designed to keep them engaged, informed, and on track—all within a simple and user-friendly interface.",
    url = "https://github.com/Abdallah-Alqiran/Fitness-App",
    image = "https://drive.google.com/uc?export=view&id=1LHqildeJORutspcTLq0RH39-s5mWJBns"
)

val project2 = ProjectUiModel(
    id = 1,
    projectName = "Github Trend Explorer",
    description = "This Android application was developed as part of the DEPI initiative. The project successfully created a platform to display popular GitHub repositories, showcasing essential details such as stars, forks, and topics. Throughout development, best practices for API integration and dynamic content rendering were implemented to ensure an optimized user experience.",
    url = "https://github.com/Abdallah-Alqiran/GitHub-Trend-Explorer-Project",
    image = "https://drive.google.com/uc?export=view&id=17dPzh3Sak8C5ASYYWWBIFLR9oM_MlC7q"
)

val project3 = ProjectUiModel(
    id = 2,
    projectName = "Meals App",
    description = "This Android application was developed as part of the DEPI initiative. The project successfully created a platform to display popular GitHub repositories, showcasing essential details such as stars, forks, and topics. Throughout development, best practices for API integration and dynamic content rendering were implemented to ensure an optimized user experience.",
    url = "https://github.com/Abdallah-Alqiran/Meals-App",
    image = "https://drive.google.com/uc?export=view&id=1oUhqh9sChodainL8o05Q59mZ2PgjdbJ8"
)

val projects: List<ProjectUiModel> = listOf(
    project,
    project2,
    project3,
    project.copy(id = 3, projectName = "hello world"),
    project.copy(id = 4, projectName = "Fake name and data"),
    project.copy(id = 5),
)

val contact = listOf(
    ContactAndAccountsUiModel(
        id = 0,
        webName = "linkedin",
        url = "https://www.linkedin.com/in/abdallah-alqiran/"
    ),
    ContactAndAccountsUiModel(
        id = 1,
        webName = "whatsapp",
        url = "https://wa.me/201016611062"
    ),
    ContactAndAccountsUiModel(
        id = 2,
        webName = "facebook",
        url = "https://www.linkedin.com/in/abdallah-alqiran/"
    ),

    )

val skills: List<SkillUiModel> = listOf(
    SkillUiModel(
        id = 0,
        skillName = "OOP and Data Structure"
    ),
    SkillUiModel(
        id = 1,
        skillName = "Design Patterns, Clean Architecture"
    ),
    SkillUiModel(
        id = 2,
        skillName = "Problem solving (1000+ problems)"
    ),
    SkillUiModel(
        id = 3,
        skillName = "Communication"
    ),
    SkillUiModel(
        id = 4,
        skillName = "TeamWork"
    ),
    SkillUiModel(
        id = 5,
        skillName = "UI/UX"
    )

)


val technologyTitle: List<TechnologyTitleUiModel> = listOf(
    TechnologyTitleUiModel(
        id = 0,
        technologyTitle = "Programming Languages",
        technologies = listOf<TechnologyUiModel>(
            TechnologyUiModel(
                id = 0,
                technologyName = "Kotlin"
            ),
            TechnologyUiModel(
                id = 1,
                technologyName = "Flutter"
            ),
            TechnologyUiModel(
                id = 2,
                technologyName = "C++"
            ),
            TechnologyUiModel(
                id = 3,
                technologyName = "HTML, CSS, JAVAScript"
            ),
            TechnologyUiModel(
                id = 4,
                technologyName = "JAVA"
            ),
        )
    ),
    TechnologyTitleUiModel(
        id = 1,
        technologyTitle = "Technologies",
        technologies = listOf<TechnologyUiModel>(
            TechnologyUiModel(
                id = 0,
                technologyName = "JetpackCompose"
            ),
            TechnologyUiModel(
                id = 1,
                technologyName = "Databases: SQL, SQLite, Firebase"
            ),
            TechnologyUiModel(
                id = 2,
                technologyName = "viewModel"
            ),
            TechnologyUiModel(
                id = 3,
                technologyName = "Dagger Hilt"
            ),
            TechnologyUiModel(
                id = 4,
                technologyName = "Room, Shared Preferences"
            ),
            TechnologyUiModel(
                id = 5,
                technologyName = "Git and Github"
            ),
        )
    ),
)

val courses: List<CourseUiModel> = listOf(
    CourseUiModel(
        id = 0,
        courseName = "DEPI (Digital Egypt Pioneers Initiative)",
        courseDescription = "Mobile app development, Technical and non-technical sessions"
    ),
    CourseUiModel(
        id = 1,
        courseName = "Database",
        courseDescription = "Offline course at Creativa Qena"
    ),
    CourseUiModel(
        id = 2,
        courseName = "Job Readiness",
        courseDescription = "At Dandara Al Ebdaa, Focused on developing skills for the professional workplace"
    ),
    CourseUiModel(
        id = 3,
        courseName = "Innovation Bootcamp",
        courseDescription = "Developed product-thinking mindset and improved problem-solving skills through hands-on, team-based challenges."
    ),
)

val experiences: List<ExperienceUiModel> = listOf(
    ExperienceUiModel(
        experienceTitle = "DEPI graduation project",
        company = "Digital Egypt Pioneers Initiative",
        description = "I played a key role in enhancing the app’s overall structure and user experience. My goal was to refactor key components, implement clean architecture, and integrate Firestore to improve code quality and maintainability. Additionally, I created several screens, managed all navigation components, and wrote the project’s README file. I collaborated closely with my team to ensure smooth feature integration and addressed performance challenges to deliver a stable and user-friendly app.",
        date = "10/2024 – 05/2025"
    ),
    ExperienceUiModel(
        experienceTitle = "Mentor at SVU-CPC",
        company = "South Valley University",
        description = "Assisting students at South Valley University in developing their problem-solving skills by guiding them through practice sheets and solving complex problems together.",
        date = "oct2024 to may2025"
    )
)


val fakeUserDataModel: UserUiModel = UserUiModel(
    userName = "Abdallah Alqiran",
    jobTitle = "Android Developer",
    userImage = "https://drive.google.com/uc?export=view&id=1n0QZ8XbQNSHjeMyogj0BdR_SqdQfPPdv",
    about = "I am a Computer Science student passionate about technology, innovation, and mobile app development. I have completed the Mobile Development track of the Digital Egypt Pioneers Initiative (DEPI), where I enhanced both my technical and soft skills.\n" +
            "\n" +
            "I work on personal projects to improve my Kotlin and Android development abilities and collaborated with a team on our DEPI graduation project to gain practical experience.\n" +
            "\n" +
            "Previously, I served as a mentor at South Valley University, helping students develop their problem-solving skills.\n" +
            "\n" +
            "I also participated in the ECPC Qualification Contest in 2024.",
    cvUrl = "https://drive.google.com/drive/folders/1H3fWSC_sh6Sic_gVzc5DmiTSNDd0pXvx",
    projects = projects,
    contactAndAccounts = contact,
    skills = skills,
    technologiesAndTools = technologyTitle,
    courses = courses,
    experiences = experiences,
    education = listOf(
        EducationUiModel(
            id = 0,
            date = "10/2023 - current",
            university = "South Valley University",
            major = "Not Specify",
        )
    )
)




val fakeUserDataModel2: UserUiModel = UserUiModel(
    userName = "Abdallah Abdallah Abdallah Abdallah Abdallah",
    jobTitle = "Android Developer",
    userImage = "https://drive.google.com/uc?export=view&id=1n0QZ8XbQNSHjeMyogj0BdR_SqdQfPPdv",
    about = "I am a Computer Science student passionate about technology, innovation, and mobile app development. I have completed the Mobile Development track of the Digital Egypt Pioneers Initiative (DEPI), where I enhanced both my technical and soft skills.\n" +
            "\n" +
            "I work on personal projects to improve my Kotlin and Android development abilities and collaborated with a team on our DEPI graduation project to gain practical experience.\n" +
            "\n" +
            "Previously, I served as a mentor at South Valley University, helping students develop their problem-solving skills.\n" +
            "\n" +
            "I also participated in the ECPC Qualification Contest in 2024.",
    cvUrl = "https://drive.google.com/drive/folders/1H3fWSC_sh6Sic_gVzc5DmiTSNDd0pXvx",
    projects = projects,
    contactAndAccounts = contact,
    skills = skills,
    technologiesAndTools = technologyTitle,
    courses = courses,
    experiences = experiences,
    education = listOf(
        EducationUiModel(
            id = 0,
            date = "10/2023 - current",
            university = "South Valley University",
            major = "Not Specify",
        )
    )
)