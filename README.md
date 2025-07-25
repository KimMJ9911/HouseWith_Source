# 🏠 HouseWith_Source

> **2025 DOIT 5팀 프로젝트 “HouseWith” 공유용 일부 소스 코드 리포지토리**

이 저장소는 **팀 HouseWith**의 프론트엔드 및 백엔드 주요 기능 중 일부 핵심 소스 코드를 공유하기 위해 제작되었습니다.  
코드 소스 공유, 팀 내 협업 및 프로젝트 회고에 도움이 되고자 구성되었으며, 전체 프로젝트가 아닌 **부분 소스 공개용** 리포입니다.

---

## 📁 프로젝트 소개

**HouseWith**는 대학교 기숙사 거주 학생들 간의 하우스메이트 모집을 도와주는 플랫폼입니다.  
함께 살고 싶은 사람을 조건에 맞게 찾고, 신청/승인 과정을 통해 매칭될 수 있는 시스템을 제공합니다.  
해당 리포에는 그 중 주요 기능 일부의 **프론트엔드 및 백엔드 소스**가 담겨 있습니다.

---

## 📌 주요 소스 파일 안내

| 파일명 | 설명 | 언어 |
|--------|------|------|
| `Main.tsx` | 🖥️ 프론트엔드 메인 페이지 구성 파일로, 전체 구조의 진입점 역할을 합니다.<br>리액트(React) 기반이며, 타입스크립트(TypeScript)로 작성되었습니다. | TypeScript |
| `ArticleRepository_copy.java` | 🔍 게시글 검색 기능을 처리하는 백엔드 레포지토리 클래스입니다.<br>QueryDSL을 활용하여 다양한 필터 조합 검색이 가능하게 설계되어 있습니다. | Java |
| `JoinMateService_Copy.java` | 📨 하우스메이트 신청/수락 관련 서비스 로직이 포함된 백엔드 클래스입니다.<br>신청 요청 처리, 상태 관리 등 주요 비즈니스 로직의 일부를 포함하고 있습니다. | Java |

---

## 🔧 사용 기술 스택

### ✅ 프론트엔드
- React.js (with TypeScript)
- Tailwind CSS
- React Query

### ✅ 백엔드
- Spring Boot
- Spring Data JPA
- QueryDSL
- PostgreSQL

### ✅ 배포
- AWS EC2
- AWS VPC
- AWS RDS

### ✅ CI
- GITHUB : [GitHub](https://github.com/YonseiDOIT/House-With)

---

## 📎 참고 사항

- 이 리포는 프로젝트 전체 코드가 아닌 일부 기능 구현 예시를 모아둔 **샘플 리포지토리**입니다.
- 실제 서비스 배포 버전과는 일부 차이가 있을 수 있으며, 프로젝트 보안을 위해 일부 민감한 로직 및 설정은 제외되어 있습니다.
- **개발 기간**: 2025년 6월 ~ 7월
- **팀명**: DOIT 5팀 (HouseWith)

---

## 👥 팀원 소개 (2025 DOIT 5팀)

| 이름 | 역할 | GitHub |
|------|------|--------|
| 곽민철 | 프론트엔드 개발 | [GitHub](https://github.com/kwakmincheol123) |
| 이여은 | 프론트앤드 개발 | [GitHub](https://github.com/yeoeun0402) |
| 최한나 | 기획 & QA | [GitHub](https://github.com/username) |
| 이나의 | 디자인  | [GitHub](https://github.com/username) |
| 조호진 | 디자인  | [GitHub](https://github.com/username) |
| 김민준 | 백엔드 & 데이터베이스 | [GitHub](https://github.com/KimMJ9911) |

> ※ 각 팀원 GitHub 링크는 실제 계정으로 교체해주세요.

---

## 💬 문의

프로젝트나 코드 관련 질문은 **Issues** 를 통해 남겨주세요.  
또는 이메일 `cvzx991119@yonsei.ac.kr` 으로 연락 주시면 확인 후 답변드리겠습니다.


