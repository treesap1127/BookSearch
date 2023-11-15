import React, {useState} from 'react';
import styles from './searchPage.css';
import Footer from "../../common/Footer";
import Navigation from "../../common/Navigation";

function SearchPage() {
    const [searchResults, setSearchResults] = useState([]);

    const handleSearch = (query) => {
        // 검색 결과를 가져오는 로직을 구현하고 setSearchResults로 결과를 설정합니다.
        // 예시: 검색 API 호출 등
    };

    return (
        <div className="index">
            <div className="div">
                <Footer/>
                <div className="group-2">
                    <div className="text-wrapper-9">Discover</div>
                    <img
                        className="img"
                        alt="Frame"
                        src="https://cdn.animaapp.com/projects/6554b898d013fc74e5940117/releases/6554b8c9411b2c1ade9b7f0b/img/frame-1.svg"
                    />
                </div>
                <p className="text-wrapper-10">궁금한 내용, 제목, 저자 등을 이용해서 검색해보세요!</p>
                <div className="overlap-wrapper">
                    <div className="overlap">
                        <div className="textbox-2">
                            <input className="text-wrapper-11" placeholder={"Search.."}/>
                            <img
                                className="frame-2"
                                alt="Frame"
                                src="https://cdn.animaapp.com/projects/6554b898d013fc74e5940117/releases/6554b8c9411b2c1ade9b7f0b/img/frame-2.svg"
                            />
                        </div>
                        <div className="textbox-3">
                            <div className="articles">All</div>
                            <img
                                className="frame-3"
                                alt="Frame"
                                src="https://cdn.animaapp.com/projects/6554b898d013fc74e5940117/releases/6554b8c9411b2c1ade9b7f0b/img/frame-3.svg"
                            />
                        </div>
                    </div>
                </div>
                <Navigation/>
                <div className="overlap-3">
                    <div className="group-3">
                        <div className="heading-link-books">Books</div>
                        <div className="heading-link">2155</div>
                        <div className="overlap-4">
                            <div className="all-wrapper">
                                <div className="all">ALL</div>
                            </div>
                            <div className="list-item">
                                <div className="overlap-5">
                                    <div className="div-book-description">
                                        <div className="link">
                                            <div className="span-authors-list">
                                                <div className="link-gerry-souter">노명우</div>
                                            </div>
                                        </div>
                                        <div className="link-gerry-souter-wrapper">
                                            <div className="link-gerry-souter">서울셀렉션</div>
                                        </div>
                                        <div className="div-badge">
                                            <div className="text-wrapper-13">ISBN: 9788992512329</div>
                                        </div>
                                        <div className="kdc-wrapper">
                                            <div className="text-wrapper-13">KDC: 594675.0</div>
                                        </div>
                                    </div>
                                    <div className="text-wrapper-14">세상물정의 사회학</div>
                                </div>
                                <div className="div-book-cover">
                                    <div className="overlap-6">
                                        <div className="ipad-jpg"/>
                                        <div className="overlap-group-wrapper">
                                            <div className="pseudo-wrapper">
                                                <div className="pseudo"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="overlap-7">
                                    <div className="div-plus-bar"/>
                                    <div className="div-plus-bar-2"/>
                                    <div className="div-plus-bar-3"/>
                                    <div className="div-plus-bar-4"/>
                                </div>
                            </div>
                        </div>
                        <div className="list-item-2">
                            <div className="overlap-5">
                                <div className="div-book-description">
                                    <div className="link">
                                        <div className="span-authors-list">
                                            <div className="link-gerry-souter">노명우</div>
                                        </div>
                                    </div>
                                    <div className="link-gerry-souter-wrapper">
                                        <div className="link-gerry-souter">서울셀렉션</div>
                                    </div>
                                    <div className="div-badge">
                                        <div className="text-wrapper-13">ISBN: 9788992512329</div>
                                    </div>
                                    <div className="kdc-wrapper">
                                        <div className="text-wrapper-13">KDC: 594675.0</div>
                                    </div>
                                </div>
                                <div className="text-wrapper-14">세상물정의 사회학</div>
                            </div>
                            <div className="div-book-cover">
                                <div className="overlap-6">
                                    <div className="ipad-jpg"/>
                                    <div className="overlap-group-wrapper">
                                        <div className="pseudo-wrapper">
                                            <div className="pseudo"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className="overlap-7">
                                <div className="div-plus-bar"/>
                                <div className="div-plus-bar-2"/>
                                <div className="div-plus-bar-3"/>
                                <div className="div-plus-bar-4"/>
                            </div>
                        </div>
                    </div>
                    <div className="group-4">
                        <div className="heading-link-books">Books</div>
                        <div className="heading-link">2155</div>
                    </div>
                    <div className="list-item-3">
                        <div className="overlap-5">
                            <div className="div-book-description">
                                <div className="link">
                                    <div className="span-authors-list">
                                        <div className="link-gerry-souter">노명우</div>
                                    </div>
                                </div>
                                <div className="link-gerry-souter-wrapper">
                                    <div className="link-gerry-souter">서울셀렉션</div>
                                </div>
                                <div className="div-badge">
                                    <div className="text-wrapper-13">ISBN: 9788992512329</div>
                                </div>
                                <div className="kdc-wrapper">
                                    <div className="text-wrapper-13">KDC: 594675.0</div>
                                </div>
                            </div>
                            <div className="text-wrapper-14">세상물정의 사회학</div>
                        </div>
                        <div className="div-book-cover">
                            <div className="overlap-6">
                                <div className="ipad-jpg"/>
                                <div className="overlap-group-wrapper">
                                    <div className="pseudo-wrapper">
                                        <div className="pseudo"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="overlap-7">
                            <div className="div-plus-bar"/>
                            <div className="div-plus-bar-2"/>
                            <div className="div-plus-bar-3"/>
                            <div className="div-plus-bar-4"/>
                        </div>
                    </div>
                </div>
                <div className="list-item-4">
                    <div className="overlap-5">
                        <div className="div-book-description">
                            <div className="link">
                                <div className="span-authors-list">
                                    <div className="link-gerry-souter">노명우</div>
                                </div>
                            </div>
                            <div className="link-gerry-souter-wrapper">
                                <div className="link-gerry-souter">서울셀렉션</div>
                            </div>
                            <div className="div-badge">
                                <div className="text-wrapper-13">ISBN: 9788992512329</div>
                            </div>
                            <div className="kdc-wrapper">
                                <div className="text-wrapper-13">KDC: 594675.0</div>
                            </div>
                        </div>
                        <div className="text-wrapper-14">세상물정의 사회학</div>
                    </div>
                    <div className="div-book-cover">
                        <div className="wzezyuf-ipad-jpg"/>
                    </div>
                    <div className="overlap-7">
                        <div className="div-plus-bar"/>
                        <div className="div-plus-bar-2"/>
                        <div className="div-plus-bar-3"/>
                        <div className="div-plus-bar-4"/>
                    </div>
                </div>
                <div className="list-item-5">
                    <div className="overlap-5">
                        <div className="div-book-description">
                            <div className="link">
                                <div className="span-authors-list">
                                    <div className="link-gerry-souter">노명우</div>
                                </div>
                            </div>
                            <div className="link-gerry-souter-wrapper">
                                <div className="link-gerry-souter">서울셀렉션</div>
                            </div>
                            <div className="div-badge">
                                <div className="text-wrapper-13">ISBN: 9788992512329</div>
                            </div>
                            <div className="kdc-wrapper">
                                <div className="text-wrapper-13">KDC: 594675.0</div>
                            </div>
                        </div>
                        <div className="text-wrapper-14">세상물정의 사회학</div>
                    </div>
                    <div className="div-book-cover">
                        <div className="wzezyuf-ipad-jpg"/>
                    </div>
                    <div className="overlap-7">
                        <div className="div-plus-bar"/>
                        <div className="div-plus-bar-2"/>
                        <div className="div-plus-bar-3"/>
                        <div className="div-plus-bar-4"/>
                    </div>
                </div>
                <div className="list-item-6">
                    <div className="overlap-5">
                        <div className="div-book-description">
                            <div className="link">
                                <div className="span-authors-list">
                                    <div className="link-gerry-souter">노명우</div>
                                </div>
                            </div>
                            <div className="link-gerry-souter-wrapper">
                                <div className="link-gerry-souter">서울셀렉션</div>
                            </div>
                            <div className="div-badge">
                                <div className="text-wrapper-13">ISBN: 9788992512329</div>
                            </div>
                            <div className="kdc-wrapper">
                                <div className="text-wrapper-13">KDC: 594675.0</div>
                            </div>
                        </div>
                        <div className="text-wrapper-14">세상물정의 사회학</div>
                    </div>
                    <div className="div-book-cover">
                        <div className="overlap-6">
                            <div className="ipad-jpg"/>
                            <div className="overlap-group-wrapper">
                                <div className="pseudo-wrapper">
                                    <div className="pseudo"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="overlap-7">
                        <div className="div-plus-bar"/>
                        <div className="div-plus-bar-2"/>
                        <div className="div-plus-bar-3"/>
                        <div className="div-plus-bar-4"/>
                    </div>
                </div>
                <div className="list-item-7">
                    <div className="overlap-5">
                        <div className="div-book-description">
                            <div className="link">
                                <div className="span-authors-list">
                                    <div className="link-gerry-souter">노명우</div>
                                </div>
                            </div>
                            <div className="link-gerry-souter-wrapper">
                                <div className="link-gerry-souter">서울셀렉션</div>
                            </div>
                            <div className="div-badge">
                                <div className="text-wrapper-13">ISBN: 9788992512329</div>
                            </div>
                            <div className="kdc-wrapper">
                                <div className="text-wrapper-13">KDC: 594675.0</div>
                            </div>
                        </div>
                        <div className="text-wrapper-14">세상물정의 사회학</div>
                    </div>
                    <div className="div-book-cover">
                        <div className="overlap-6">
                            <div className="ipad-jpg"/>
                            <div className="overlap-group-wrapper">
                                <div className="div-wrapper">
                                    <div className="pseudo"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="overlap-7">
                        <div className="div-plus-bar"/>
                        <div className="div-plus-bar-2"/>
                        <div className="div-plus-bar-3"/>
                        <div className="div-plus-bar-4"/>
                    </div>
                </div>
                <div className="list-item-8">
                    <div className="overlap-5">
                        <div className="div-book-description">
                            <div className="link">
                                <div className="span-authors-list">
                                    <div className="link-gerry-souter">노명우</div>
                                </div>
                            </div>
                            <div className="link-gerry-souter-wrapper">
                                <div className="link-gerry-souter">서울셀렉션</div>
                            </div>
                            <div className="div-badge">
                                <div className="text-wrapper-13">ISBN: 9788992512329</div>
                            </div>
                            <div className="kdc-wrapper">
                                <div className="text-wrapper-13">KDC: 594675.0</div>
                            </div>
                        </div>
                        <div className="text-wrapper-14">세상물정의 사회학</div>
                    </div>
                    <div className="div-book-cover">
                        <div className="wzezyuf-ipad-jpg"/>
                    </div>
                    <div className="overlap-7">
                        <div className="div-plus-bar"/>
                        <div className="div-plus-bar-2"/>
                        <div className="div-plus-bar-3"/>
                        <div className="div-plus-bar-4"/>
                    </div>
                </div>
                <div className="list-item-9">
                    <div className="overlap-5">
                        <div className="div-book-description">
                            <div className="link">
                                <div className="span-authors-list">
                                    <div className="link-gerry-souter">노명우</div>
                                </div>
                            </div>
                            <div className="link-gerry-souter-wrapper">
                                <div className="link-gerry-souter">서울셀렉션</div>
                            </div>
                            <div className="div-badge">
                                <div className="text-wrapper-13">ISBN: 9788992512329</div>
                            </div>
                            <div className="kdc-wrapper">
                                <div className="text-wrapper-13">KDC: 594675.0</div>
                            </div>
                        </div>
                        <div className="text-wrapper-14">세상물정의 사회학</div>
                    </div>
                    <div className="div-book-cover">
                        <div className="wzezyuf-ipad-jpg"/>
                    </div>
                    <div className="overlap-7">
                        <div className="div-plus-bar"/>
                        <div className="div-plus-bar-2"/>
                        <div className="div-plus-bar-3"/>
                        <div className="div-plus-bar-4"/>
                    </div>
                </div>
                <div className="list-item-10">
                    <div className="overlap-5">
                        <div className="div-book-description">
                            <div className="link">
                                <div className="span-authors-list">
                                    <div className="link-gerry-souter">노명우</div>
                                </div>
                            </div>
                            <div className="link-gerry-souter-wrapper">
                                <div className="link-gerry-souter">서울셀렉션</div>
                            </div>
                            <div className="div-badge">
                                <div className="text-wrapper-13">ISBN: 9788992512329</div>
                            </div>
                            <div className="kdc-wrapper">
                                <div className="text-wrapper-13">KDC: 594675.0</div>
                            </div>
                        </div>
                        <div className="text-wrapper-14">세상물정의 사회학</div>
                    </div>
                    <div className="div-book-cover">
                        <div className="ipad-jpg-2"/>
                    </div>
                    <div className="overlap-7">
                        <div className="div-plus-bar"/>
                        <div className="div-plus-bar-2"/>
                        <div className="div-plus-bar-3"/>
                        <div className="div-plus-bar-4"/>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default SearchPage;
