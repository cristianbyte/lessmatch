## Backend Overview 🔧  

The backend of **Universo** is built with **Java** and **Spring Boot**, offering a structured and secure REST API  
designed to manage emotional connections between users through shared musical experiences.

It handles the full lifecycle of user pairings, including creation, matching, and interaction through song lyrics.  
It supports user-driven verse selections, manages the pairing logic between two individuals, and retrieves  
popular song data for dynamic discovery and engagement.

Main responsibilities of the API include:
- Creating and updating emotional connections between users (pairings)
- Handling lyric-level selections to generate shared emotional insights
- Managing expiration of unused or incomplete pairings
- Retrieving the most listened and relevant songs for personalized exploration
- Providing structured access to pairing and song data, with filtering by user or code

The system is built with domain-driven design principles and includes secure JWT authentication,  
CORS configuration for frontend communication, and is container-ready for cloud deployment.

---

![Preview](https://github.com/cristianbyte/uni-verso/blob/master/public/images/uni-verso-app.png?raw=true)

---

## 🛠️ Technologies Used  
- **Frontend:** React + Vite  
- **Styling:** CSS Modules  
- **Backend:** Java + Spring Boot  
- **Database:** Postgresql
- **Authentication:** JWT (JSON Web Tokens)  
- **Hosting:** Render (API) + Vercel (Frontend)  
- **Deployment:** Docker  

## 📁 Backend Structure (Spring Boot)
- `api/` → Controllers and endpoints  
- `config/` → Security, JWT, and general config  
- `domain/` → Core entities and business logic  
- `infrastructure/` → Database layer and adapters  
- `util/` → Helper utilities and constants  

---

🔗 **Live Demo**: [universo.coder.red](https://universo.coder.red)

---

✨ This project was created to reinforce my knowledge in both **React** and **Spring Boot**, while building something beautiful and meaningful.