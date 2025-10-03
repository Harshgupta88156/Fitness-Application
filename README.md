# 🏃‍♂️ FitLife - AI-Powered Fitness Tracking Platform

A comprehensive microservices-based fitness tracking application with AI-powered recommendations, built with Spring Boot and React.

## 🌟 Features

### Frontend (React + Vite)
- 🎨 **Modern UI/UX** - Beautiful, responsive design with Material-UI  
- 📊 **Interactive Dashboard** - Real-time statistics and progress tracking  
- 🏃‍♂️ **Activity Management** - Track running, walking, and cycling activities  
- 🤖 **AI Recommendations** - Personalized fitness insights powered by Gemini AI  
- 🔐 **Secure Authentication** - OAuth2 integration with Keycloak  
- 📱 **Mobile-First Design** - Fully responsive across all devices  
- ✨ **Engaging Animations** - Smooth transitions and interactive elements  

### Backend (Spring Boot Microservices)
- 🏗️ **Microservices Architecture** - Scalable, maintainable service design  
- 🔄 **Event-Driven Communication** - Kafka for asynchronous processing  
- 🤖 **AI Integration** - Gemini AI for intelligent fitness recommendations  
- 🔐 **Security** - JWT-based authentication with Keycloak  
- 📊 **Service Discovery** - Eureka for service registration and discovery  
- ⚙️ **Configuration Management** - Centralized config with Spring Cloud Config  
- 🚪 **API Gateway** - Single entry point with load balancing  

## 🏗️ Architecture

<img width="1142" height="547" alt="Screenshot (802)" src="https://github.com/user-attachments/assets/1b0600c0-2d87-497e-93f3-8aa82922f0d3" />

## ✨ Project Structure

fitlife-project/
├── fitness-frontend/          # React frontend application
│   ├── src/
│   │   ├── components/        # Reusable UI components
│   │   ├── services/          # API services
│   │   ├── store/             # Redux store
│   │   └── App.jsx            # Main app component
│   ├── Dockerfile             # Frontend Docker configuration
│   └── package.json           # Frontend dependencies
├── activity/                  # Activity microservice
│   ├── src/main/java/
│   ├── Dockerfile
│   └── pom.xml
├── AIService/                 # AI recommendations service
│   ├── src/main/java/
│   ├── Dockerfile
│   └── pom.xml
├── UserService/               # User management service
│   ├── src/main/java/
│   ├── Dockerfile
│   └── pom.xml
├── ApiGateway/                # API Gateway service
│   ├── src/main/java/
│   ├── Dockerfile
│   └── pom.xml
├── configServer/              # Configuration server
│   ├── src/main/resources/config/
│   ├── Dockerfile
│   └── pom.xml
├── eureka/                    # Service discovery server
│   ├── src/main/java/
│   ├── Dockerfile
│   └── pom.xml
├── docker-compose.yml         # Infrastructure services
├── docker-compose-full.yml    # Complete application stack
├── start-app.sh               # Linux/Mac startup script
├── start-app.bat              # Windows startup script
└── README.md                  # This file

