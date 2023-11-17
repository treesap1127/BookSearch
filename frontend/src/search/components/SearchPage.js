import React, {useState} from 'react';
import css from './searchPage.css';
import footerCss from '../../common/footer/footer.css';
import Navigation from "../../common/navigation/Navigation";
import Footer from "../../common/footer/Footer";

function SearchPage() {
    const [searchResults, setSearchResults] = useState([]);

    const handleSearch = (query) => {
        // 검색 결과를 가져오는 로직을 구현하고 setSearchResults로 결과를 설정합니다.
        // 예시: 검색 API 호출 등
    };

    return (
        <div className="container">
            <Navigation/>
            <div className="wrapper">
                <div>
                    <div className="intro-con">
                        <div>
                            <img
                                className="compass"
                                alt="compass"
                                src="https://cdn.animaapp.com/projects/6554b898d013fc74e5940117/releases/6554b8c9411b2c1ade9b7f0b/img/frame-1.svg"
                            />
                        </div>
                        <div><h3 className="intro-text1">Discover</h3></div>
                        <div><p className="intro-text2">궁금한 내용, 제목, 저자 등을 검색해보세요!</p></div>
                    </div>
                    <div className="search-con-main">
                        <div>
                            <img
                                className="search-icon-main"
                                src="https://cdn.animaapp.com/projects/6554b898d013fc74e5940117/releases/6554b8c9411b2c1ade9b7f0b/img/frame-2.svg"
                            />
                        </div>
                        <input className="search-main" placeholder={"Search"}/>
                        <div>
                            <button className="search-main-btn">모두</button>
                            <svg className="search-main-arrow" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none">
                                <path d="M4.60909 7.00003L3 8.60912L12 17.6091L21 8.60912L19.3909 7.00003L12 14.3909L4.60909 7.00003Z" fill="white"/>
                            </svg>
                        </div>

                    </div>
                    <div className="result-con">
                        <div className="result-text-wr">
                            <p>Books</p>
                            <p>10</p>
                        </div>
                        <div className="book-con">
                            <div className="book-each">
                                <div>
                                    <img className="book-img" src=""/>
                                </div>
                                <p className="title">세상물정의 사회학</p>
                                <p className="author">노명우</p>
                                <p className="publisher">니은서점</p>
                                <div className="isbn">
                                    <p>ISBN: 9788992512329</p>
                                </div>
                                <div className="isbn">
                                    <p>KDC: 594675.0</p>
                                </div>
                                <div className="more-btn">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16" fill="none">
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
                                <div className="desc">
                                    <p>책 소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개
                                        내용책
                                        소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개 내용ㅍ</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <Footer/>
        </div>
    );
}

export default SearchPage;
