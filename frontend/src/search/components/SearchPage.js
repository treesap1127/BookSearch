import React, {useEffect, useState} from 'react';
import css from './searchPage.css';
import footerCss from '../../common/footer/footer.css';
import Navigation from "../../common/navigation/Navigation";
import Footer from "../../common/footer/Footer";
import Book from "./Book";
import axios from "axios";

function SearchPage() {
    const [result, setResult] = useState([]);
    const [keyword, setKeyword] = useState("");
    const [selectedBookIndex, setSelectedBookIndex] = useState(-1);
    const toggleDesc = (index) => {
        setSelectedBookIndex(index);
    };

    async function search() {
        try {
            const response = await axios.get(
                'http://localhost:8081/api/book/search',
                {
                    params: {
                        keyword: keyword
                    }
                }
            );
            setResult(response.data);
        } catch (error) {
            console.log(error);
            throw error;
        }
    }

    function handleKeyPress(e) {
        if (e.key === 'Enter') search();
    }

    return (
        <div className="container">
            <Navigation/>
            <div className="wrapper">
                <div>
                    <div className="intro-con">
                        <div className="intro-text1-con">
                            <div className="intro-text1-wr">
                                <div className="compass-wr">
                                    <img
                                        className="compass"
                                        alt="compass"
                                        src="https://cdn.animaapp.com/projects/6554b898d013fc74e5940117/releases/6554b8c9411b2c1ade9b7f0b/img/frame-1.svg"
                                    />
                                </div>
                                <div><h3 className="intro-text1">Discover</h3></div>
                            </div>
                            <div><p className="intro-text2">궁금한 내용, 제목, 저자 등을 검색해보세요!</p></div>
                        </div>
                    </div>
                    <div className="search-con-main">
                        <div className="search-icon-main-wr">
                            <img
                                className="search-icon-main"
                                src="https://cdn.animaapp.com/projects/6554b898d013fc74e5940117/releases/6554b8c9411b2c1ade9b7f0b/img/frame-2.svg"
                            />
                        </div>
                        <input
                            className="search-main"
                            placeholder={"Search"}
                            value={keyword}
                            onChange={(e) => setKeyword(e.target.value)}
                            onKeyDown={handleKeyPress}
                        />
                        <div>
                            <button className="search-main-btn" onClick={search}>모두</button>
                            <svg className="search-main-arrow" xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                                 viewBox="0 0 24 24" fill="none">
                                <path
                                    d="M4.60909 7.00003L3 8.60912L12 17.6091L21 8.60912L19.3909 7.00003L12 14.3909L4.60909 7.00003Z"
                                    fill="white"/>
                            </svg>
                        </div>

                    </div>
                    <div className="result-con">
                        <div className="result-text-wr">
                            <p>Books</p>
                            <p>{result.length}</p>
                        </div>
                        <div className="book-con">
                            {result.map((item, idx) => (
                                <Book
                                    key={idx}
                                    item={item}
                                    idx={idx}
                                    isToggled={idx === selectedBookIndex}
                                    toggleDesc={toggleDesc}
                                />
                            ))}
                        </div>
                    </div>
                </div>
            </div>
            <Footer/>
        </div>
    );
}

export default SearchPage;
