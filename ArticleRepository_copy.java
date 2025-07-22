/**
     * 6/26 -
     * article_Id 로 게시글 검색
     *
     * 7/8 - 수장
     * articleId로 해당 방의 정보들 전체 반환
     * 방에 속한 member 들 전부 반환
     */
    @Override
    public Optional<DormitoryDTO> findArticleAndAcceptedMember(Long articleId) {
        Map<Long , DormitoryDTO> result = queryFactory
                .from(article)
                .leftJoin(article.joinRequests , joinRequest)
                .leftJoin(joinRequest.member , member)
                .on(joinRequest.joinStatus.in(JoinStatus.ACCEPTED , JoinStatus.OWNER))
                .where(article.id.eq(articleId))
                .transform(GroupBy.groupBy(article.id).as(
                        Projections.constructor(DormitoryDTO.class ,
                                article.id ,
                                article.createdTime ,
                                article.dormitory ,
                                article.title ,
                                article.quarter ,
                                article.access_max ,
                                article.comment ,
                                article.open_url ,
                                list(
                                        Projections.constructor(MemberDTO.class ,
                                                member.id ,
                                                member.name ,
                                                member.introduction_comment ,
                                                member.phone ,
                                                member.email ,
                                                member.nickname ,
                                                member.sex ,
                                                member.dormitoryName ,
                                                member.livingPattern).skipNulls()
                                ))
                ));

        DormitoryDTO dto = result.get(articleId);

        if (dto == null) return Optional.empty();
        else return Optional.of(dto);
    }

    /**
     *
     * title_param -> 검색 키워드
     * 6/23 -
     * 검색 기능 추가 -> 비슷한 단어/키워드 검색
     * 페이징 기능 추가로 한 화면당 10개 -> 수정 가능
     * 인원 수 , 룸메이트 생활조건(중복)
     *
     */
    @Override
    public List<ArticleDTO> findArticleByKeywords(String search_key ,
                                                  String motion ,
                                                  String smoke ,
                                                  String sleep_time ,
                                                  String available_at ,
                                                  String dormitory) {
        return queryFactory
                .select(new QArticleDTO(
                        article.id ,
                        article.owner_nickname ,
                        article.owner ,
                        article.createdTime ,
                        article.dormitory ,
                        article.title ,
                        article.quarter ,
                        article.join_member_count ,
                        article.access_max ,
                        article.comment ,
                        article.open_url))
                .from(article)
                .leftJoin(article.roomKeyword , roomKeyword)
                .where(
                        searchKeyCondition(search_key) ,
                        motionEq(motion) ,
                        smokeEq(smoke) ,
                        sleep_timeEq(sleep_time) ,
                        available_atEq(available_at) ,
                        dormitoryEq(dormitory)
                        )
                .fetch();
    }


    /**
     * 6/25 -
     * 검색 키워드 추가 - 검색창
     *
     * 7/4 - 수정 완료
     * 검색창에서 기숙사 이름 검색을 제외했습니다.
     */
    private BooleanExpression searchKeyCondition(String search_key) {
        if (search_key == null || search_key.trim().isEmpty()) return null;

        String keyword = "%" + search_key.trim() + "%";
        return article.title.like(keyword).or(article.comment.like(keyword));
    }


    /**
     * 6/25 -
     * 검색 조건 키워드 추가 - 버튼
     *
     * 7/13 - 수정
     * 검색 키워드에 기숙사 이름도 추가
     */

    private BooleanExpression motionEq(String motion) {
        return motion != null ? roomKeyword.motion.eq(motion) : null;
    }

    private BooleanExpression smokeEq(String smoke) {
        return smoke != null ? roomKeyword.smoke.eq(smoke) : null;
    }

    private BooleanExpression sleep_timeEq(String sleep_time) {
        return sleep_time != null ? roomKeyword.sleep_time.eq(sleep_time) : null;
    }

    private BooleanExpression available_atEq(String available_eat) {
        return available_eat != null ? roomKeyword.available_eat.eq(available_eat) : null;
    }

    private BooleanExpression dormitoryEq(String dormitory) {
        return dormitory != null ? article.dormitory.eq(dormitory) : null;
    }
