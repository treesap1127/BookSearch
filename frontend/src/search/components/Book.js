import React, {useState} from 'react';
import css from './book.css'

function Book() {
    const [searchResults, setSearchResults] = useState([]);

    const handleSearch = (query) => {
        // 검색 결과를 가져오는 로직을 구현하고 setSearchResults로 결과를 설정합니다.
        // 예시: 검색 API 호출 등
    };

    return (
        <div className="book-each">
            <div className="book-top">
                <div>
                    <img className="book-img" src=""/>
                </div>
                <div className="book-info">
                    <p className="title">세상물정의 사회학</p>
                    <p className="author">노명우</p>
                    <p className="publisher">니은서점</p>
                    <div className="isbn-wr">
                        <div className="isbn">
                            <p>ISBN: 9788992512329</p>
                        </div>
                        <div className="isbn">
                            <p>KDC: 594675.0</p>
                        </div>
                    </div>
                </div>
                <div className="more-btn">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                         viewBox="0 0 16 16"
                         fill="none">
                        <g clip-path="url(#clip0_41_68)">
                            <rect y="7" width="8" height="2" fill="#9A938D"/>
                            <rect x="8" y="7" width="8" height="2" fill="#9A938D"/>
                            <rect x="7" width="2" height="8" fill="#9A938D"/>
                            <rect x="7" y="8" width="2" height="8" fill="#9A938D"/>
                        </g>
                        <defs>
                            <clipPath id="clip0_41_68">
                                <rect width="16" height="16" fill="white"/>
                            </clipPath>
                        </defs>
                    </svg>
                </div>
            </div>
            <div className="book-bottom">
                <div className="desc hide">
                    <p>책 소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개
                        내용책 소개
                        내용책
                        소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개 내용ㅍ</p>
                </div>
            </div>
        </div>
    );
}

export default Book;
