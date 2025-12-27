<div align="center">

<!-- PROJECT LOGO -->
<svg width="200" height="200" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 200 200">
  <defs>
    <linearGradient id="bgGradient" x1="0%" y1="0%" x2="100%" y2="100%">
      <stop offset="0%" style="stop-color:#6366f1;stop-opacity:1" />
      <stop offset="100%" style="stop-color:#8b5cf6;stop-opacity:1" />
    </linearGradient>
    <linearGradient id="nodeGradient" x1="0%" y1="0%" x2="100%" y2="100%">
      <stop offset="0%" style="stop-color:#f59e0b;stop-opacity:1" />
      <stop offset="100%" style="stop-color:#ef4444;stop-opacity:1" />
    </linearGradient>
    <filter id="glow">
      <feGaussianBlur stdDeviation="3" result="coloredBlur"/>
      <feMerge>
        <feMergeNode in="coloredBlur"/>
        <feMergeNode in="SourceGraphic"/>
      </feMerge>
    </filter>
  </defs>
  <circle cx="100" cy="100" r="95" fill="url(#bgGradient)" opacity="0.95"/>
  <circle cx="100" cy="100" r="80" fill="none" stroke="white" stroke-width="1.5" opacity="0.3"/>
  <line x1="100" y1="60" x2="70" y2="100" stroke="white" stroke-width="2" opacity="0.4"/>
  <line x1="100" y1="60" x2="130" y2="100" stroke="white" stroke-width="2" opacity="0.4"/>
  <line x1="70" y1="100" x2="85" y2="140" stroke="white" stroke-width="2" opacity="0.4"/>
  <line x1="130" y1="100" x2="115" y2="140" stroke="white" stroke-width="2" opacity="0.4"/>
  <line x1="85" y1="140" x2="115" y2="140" stroke="white" stroke-width="2" opacity="0.4"/>
  <line x1="70" y1="100" x2="130" y2="100" stroke="white" stroke-width="2" opacity="0.4"/>
  <circle cx="100" cy="60" r="12" fill="white" filter="url(#glow)"/>
  <circle cx="100" cy="60" r="9" fill="url(#nodeGradient)"/>
  <circle cx="70" cy="100" r="10" fill="white" filter="url(#glow)"/>
  <circle cx="70" cy="100" r="7.5" fill="url(#nodeGradient)"/>
  <circle cx="130" cy="100" r="10" fill="white" filter="url(#glow)"/>
  <circle cx="130" cy="100" r="7.5" fill="url(#nodeGradient)"/>
  <circle cx="85" cy="140" r="10" fill="white" filter="url(#glow)"/>
  <circle cx="85" cy="140" r="7.5" fill="url(#nodeGradient)"/>
  <circle cx="115" cy="140" r="10" fill="white" filter="url(#glow)"/>
  <circle cx="115" cy="140" r="7.5" fill="url(#nodeGradient)"/>
  <circle cx="100" cy="100" r="16" fill="white" filter="url(#glow)"/>
  <circle cx="100" cy="100" r="13" fill="url(#nodeGradient)"/>
  <text x="100" y="110" font-family="Arial, sans-serif" font-size="18" font-weight="bold" fill="white" text-anchor="middle">S</text>
</svg>

# SocialGraph Engine

### Enterprise-Grade Social Networking Backend

*Built with Spring Boot | Designed for Scale | Ready for Production*

<br>

<!-- BADGES -->
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen?style=for-the-badge&logo=github-actions)](https://github.com/yourusername/social-graph-engine/actions)
[![Java Version](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk&logoColor=white)](https://adoptium.net/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.7-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Oracle DB](https://img.shields.io/badge/Oracle-19c+-F80000?style=for-the-badge&logo=oracle&logoColor=white)](https://www.oracle.com/database/)


<br>

<!-- DEMO BANNER -->
<svg width="1200" height="300" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 300">
  <defs>
    <linearGradient id="bannerBg" x1="0%" y1="0%" x2="100%" y2="100%">
      <stop offset="0%" style="stop-color:#0f172a;stop-opacity:1" />
      <stop offset="50%" style="stop-color:#1e293b;stop-opacity:1" />
      <stop offset="100%" style="stop-color:#0f172a;stop-opacity:1" />
    </linearGradient>
    <linearGradient id="accentGradient" x1="0%" y1="0%" x2="100%" y2="0%">
      <stop offset="0%" style="stop-color:#6366f1;stop-opacity:1" />
      <stop offset="50%" style="stop-color:#8b5cf6;stop-opacity:1" />
      <stop offset="100%" style="stop-color:#ec4899;stop-opacity:1" />
    </linearGradient>
    <linearGradient id="glowGradient" x1="0%" y1="0%" x2="100%" y2="100%">
      <stop offset="0%" style="stop-color:#6366f1;stop-opacity:0.3" />
      <stop offset="100%" style="stop-color:#8b5cf6;stop-opacity:0.1" />
    </linearGradient>
    <filter id="bannerGlow">
      <feGaussianBlur stdDeviation="4" result="coloredBlur"/>
      <feMerge>
        <feMergeNode in="coloredBlur"/>
        <feMergeNode in="SourceGraphic"/>
      </feMerge>
    </filter>
  </defs>
  <rect width="1200" height="300" fill="url(#bannerBg)"/>
  <g opacity="0.05">
    <line x1="0" y1="50" x2="1200" y2="50" stroke="white" stroke-width="0.5"/>
    <line x1="0" y1="100" x2="1200" y2="100" stroke="white" stroke-width="0.5"/>
    <line x1="0" y1="150" x2="1200" y2="150" stroke="white" stroke-width="0.5"/>
    <line x1="0" y1="200" x2="1200" y2="200" stroke="white" stroke-width="0.5"/>
    <line x1="0" y1="250" x2="1200" y2="250" stroke="white" stroke-width="0.5"/>
    <line x1="200" y1="0" x2="200" y2="300" stroke="white" stroke-width="0.5"/>
    <line x1="400" y1="0" x2="400" y2="300" stroke="white" stroke-width="0.5"/>
    <line x1="600" y1="0" x2="600" y2="300" stroke="white" stroke-width="0.5"/>
    <line x1="800" y1="0" x2="800" y2="300" stroke="white" stroke-width="0.5"/>
    <line x1="1000" y1="0" x2="1000" y2="300" stroke="white" stroke-width="0.5"/>
  </g>
  <circle cx="100" cy="80" r="60" fill="url(#glowGradient)" opacity="0.4"/>
  <circle cx="1100" cy="220" r="80" fill="url(#glowGradient)" opacity="0.3"/>
  <g transform="translate(150, 150)">
    <line x1="-40" y1="-40" x2="0" y2="0" stroke="#6366f1" stroke-width="2" opacity="0.4"/>
    <line x1="40" y1="-40" x2="0" y2="0" stroke="#6366f1" stroke-width="2" opacity="0.4"/>
    <line x1="-40" y1="40" x2="0" y2="0" stroke="#6366f1" stroke-width="2" opacity="0.4"/>
    <line x1="40" y1="40" x2="0" y2="0" stroke="#6366f1" stroke-width="2" opacity="0.4"/>
    <circle cx="-40" cy="-40" r="8" fill="#6366f1" filter="url(#bannerGlow)"/>
    <circle cx="40" cy="-40" r="8" fill="#8b5cf6" filter="url(#bannerGlow)"/>
    <circle cx="-40" cy="40" r="8" fill="#ec4899" filter="url(#bannerGlow)"/>
    <circle cx="40" cy="40" r="8" fill="#f59e0b" filter="url(#bannerGlow)"/>
    <circle cx="0" cy="0" r="12" fill="url(#accentGradient)" filter="url(#bannerGlow)"/>
  </g>
  <g transform="translate(600, 150)">
    <text x="0" y="-50" font-family="Arial, sans-serif" font-size="48" font-weight="bold" fill="white" text-anchor="middle" filter="url(#bannerGlow)">SocialGraph Engine</text>
    <text x="0" y="-10" font-family="Arial, sans-serif" font-size="20" fill="#94a3b8" text-anchor="middle">Enterprise-Grade Social Networking Backend</text>
    <rect x="-150" y="10" width="300" height="3" fill="url(#accentGradient)" rx="1.5"/>
    <g transform="translate(-120, 50)">
      <circle cx="0" cy="0" r="20" fill="#1e293b" stroke="#6366f1" stroke-width="2"/>
      <text x="0" y="7" font-family="Arial, sans-serif" font-size="14" font-weight="bold" fill="#6db33f" text-anchor="middle">SB</text>
      <circle cx="60" cy="0" r="20" fill="#1e293b" stroke="#8b5cf6" stroke-width="2"/>
      <text x="60" y="7" font-family="Arial, sans-serif" font-size="14" font-weight="bold" fill="#f89820" text-anchor="middle">J</text>
      <circle cx="120" cy="0" r="20" fill="#1e293b" stroke="#ec4899" stroke-width="2"/>
      <text x="120" y="7" font-family="Arial, sans-serif" font-size="14" font-weight="bold" fill="#f80000" text-anchor="middle">O</text>
      <circle cx="180" cy="0" r="20" fill="#1e293b" stroke="#f59e0b" stroke-width="2"/>
      <text x="180" y="7" font-family="Arial, sans-serif" font-size="14" font-weight="bold" fill="#dc382d" text-anchor="middle">R</text>
      <circle cx="240" cy="0" r="20" fill="#1e293b" stroke="#6366f1" stroke-width="2"/>
      <text x="240" y="7" font-family="Arial, sans-serif" font-size="12" font-weight="bold" fill="#d63aff" text-anchor="middle">JWT</text>
    </g>
  </g>
  <g transform="translate(1000, 100)">
    <rect x="0" y="0" width="120" height="30" rx="4" fill="#1e293b" stroke="#6366f1" stroke-width="2"/>
    <text x="60" y="20" font-family="monospace" font-size="12" fill="#6366f1" text-anchor="middle">POST /api/auth</text>
    <rect x="0" y="40" width="120" height="30" rx="4" fill="#1e293b" stroke="#8b5cf6" stroke-width="2"/>
    <text x="60" y="60" font-family="monospace" font-size="12" fill="#8b5cf6" text-anchor="middle">GET /posts</text>
    <rect x="0" y="80" width="120" height="30" rx="4" fill="#1e293b" stroke="#ec4899" stroke-width="2"/>
    <text x="60" y="100" font-family="monospace" font-size="12" fill="#ec4899" text-anchor="middle">PUT /friends</text>
  </g>
  <rect x="0" y="295" width="1200" height="5" fill="url(#accentGradient)"/>
  <g opacity="0.6">
    <circle cx="300" cy="50" r="3" fill="#6366f1">
      <animate attributeName="cy" values="50;60;50" dur="3s" repeatCount="indefinite"/>
      <animate attributeName="opacity" values="0.6;1;0.6" dur="3s" repeatCount="indefinite"/>
    </circle>
    <circle cx="900" cy="80" r="2" fill="#8b5cf6">
      <animate attributeName="cy" values="80;70;80" dur="4s" repeatCount="indefinite"/>
      <animate attributeName="opacity" values="0.6;1;0.6" dur="4s" repeatCount="indefinite"/>
    </circle>
    <circle cx="450" cy="260" r="2.5" fill="#ec4899">
      <animate attributeName="cy" values="260;250;260" dur="3.5s" repeatCount="indefinite"/>
      <animate attributeName="opacity" values="0.6;1;0.6" dur="3.5s" repeatCount="indefinite"/>
    </circle>
    <circle cx="750" cy="40" r="2" fill="#f59e0b">
      <animate attributeName="cy" values="40;50;40" dur="4.5s" repeatCount="indefinite"/>
      <animate attributeName="opacity" values="0.6;1;0.6" dur="4.5s" repeatCount="indefinite"/>
    </circle>
  </g>
</svg>

</div>



## ✨ What is SocialGraph Engine?

<div align="center">

**SocialGraph Engine** is a production-ready, enterprise-grade backend system that powers modern social networking applications. Built with Spring Boot and following clean architecture principles, it provides a comprehensive RESTful API for user management, social connections, content creation, and real-time interactions.

</div>

> **⚠️ Development Status**  
> This project is under active development. Core features are functional and tested, while advanced features are being incrementally rolled out. The API contract may evolve during this phase.



---

---

## 🛠️ Technology Stack

<div align="center">

### Core Technologies

<table>
<tr>
<td align="center" width="20%">
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="60" height="60" alt="Java">
<br><strong>Java 17</strong>
<br><sub>LTS Release</sub>
</td>
<td align="center" width="20%">
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" width="60" height="60" alt="Spring Boot">
<br><strong>Spring Boot 3.5</strong>
<br><sub>Modern Framework</sub>
</td>
<td align="center" width="20%">
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/oracle/oracle-original.svg" width="60" height="60" alt="Oracle">
<br><strong>Oracle 19c+</strong>
<br><sub>Enterprise DB</sub>
</td>
<td align="center" width="20%">
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/redis/redis-original.svg" width="60" height="60" alt="Redis">
<br><strong>Redis</strong>
<br><sub>Caching Layer</sub>
</td>
</tr>
</table>

</div>

```
