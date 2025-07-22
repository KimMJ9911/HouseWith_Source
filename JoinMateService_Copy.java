
static String ERROR = "잘못된 요청입니다. ERROR CODE : ";

    @Value("${ErrorCode.Request.UNAVAILABLE_REQUEST_NULL}")
    private String error_NULL;

    @Value("${ErrorCode.Request.REQUEST_OVERFLOW}")
    private String error_OVERFLOW;

    @Value("${ErrorCode.Request.VALUE_DUPLICATED}")
    private String error_DUPLICATED;
/**
     * @param articleId : 방 ID
     * @param memberId : 가입 신청한 사람의 ID
     *
     * 7/8 - 수정
     * 비효율적인 쿼리문 개선
     *
     * 중복 인원 가입 불가 처리 개발 필요 -> 수정 완료
     * 가입 가능 인원 제한 설정 - 추가 개발 필요 (일단 3명까지만 해놓음) -> 수정 완료
     */
//가입 절차용 메서드 (Service)
public void ToJoin(
            Long articleId ,
            Long memberId
    ) {
        //중복 인원 가입 신청 예외처리 안해놓음 -> 수정 완료
        Article article =
                articleRepository.findArticlesById(articleId);

        //인원 수 제한 설정
        if (article == null)
            throw new IllegalStateException(ERROR + error_NULL + "해당하는 방이 없습니다.");

        long val = joinRequestRepository.countByMember(memberId);
        if (val > article.getAccess_max()) {
            throw new IllegalStateException(ERROR + error_OVERFLOW);
        }

        Member member =
                memberRepository.findByMemberId(memberId);
        //방에 신청한 모든 인원들(소유자 , 대기자 , 수락 , 거절)을 받아옵니다.
        List<Member> memberList =
                memberRepository.findAllMemberAtArticle(articleId);

        //받아올 멤버가 없는 경우
        if (member == null )
            throw new IllegalStateException(ERROR + error_NULL + " : 해당하는 멤버가 없습니다.");

        //중복 멤버가 껴있는 경우
        //모든 리스트에서 멤버 id 를 대조 비교 (신청자 or 소유자)
        for (Member member_in : memberList) {
            if (Objects.equals(member_in.getId().toString(), String.valueOf(memberId)))
                throw new IllegalStateException(ERROR + error_DUPLICATED + " : ");
        }

        JoinRequest join = new JoinRequest(
                article , member , JoinStatus.WAITING
        );

        joinRequestRepository.save(join);
        articleRepository.save(article);
    }
