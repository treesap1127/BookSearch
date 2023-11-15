// import React, {useState} from 'react';
// import styles from './SearchPage.module.css';
//
// function SearchPage() {
//     const [searchResults, setSearchResults] = useState([]);
//
//     const handleSearch = (query) => {
//         // 검색 결과를 가져오는 로직을 구현하고 setSearchResults로 결과를 설정합니다.
//         // 예시: 검색 API 호출 등
//     };
//
//     return (
//         <div className={styles.main}>
//             <header>
//                 <div>아이콘</div>
//                 <div><span>돋보기</span><input placeholder={"search"}/></div>
//                 <div>설정 아이콘</div>
//             </header>
//             <div>
//                 <div className={"styles.intro-con"}>
//                     <div>나침반</div>
//                     <div><h3>Discover</h3></div>
//                     <div><p>궁금한 내용, 제목, 저자 등을 검색해보세요!</p></div>
//                 </div>
//                 <div className={styles.search-input-con}>
//                     <input placeholder={"Search"}/>
//                     <div>
//                         <button>모두</button>
//                     </div>
//                 </div>
//                 <div className={styles.result-con}>
//                     <div className={styles.result-text-wr}>
//                         <p>Books</p>
//                         <p>10</p>
//                     </div>
//                     <div className={styles.book-con}>
//                         <div className={styles.book-each}>
//                             <img/>
//                             <p>세상물정의 사회학</p>
//                             <p>노명우</p>
//                             <p>니은서점</p>
//                             <div>
//                                 <p>ISBN: 9788992512329</p>
//                             </div>
//                             <div>
//                                 <p>KDC: 594675.0</p>
//                             </div>
//                             <div>+</div>
//                             <div>
//                                 <p>책 소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개 내용책
//                                     소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개 내용책 소개 내용ㅍ</p>
//                             </div>
//                         </div>
//                     </div>
//                     <footer>
//                         <div>아이콘</div>
//                         <p>Quili-</p>
//                         <div className={styles.footer-nav-con}>
//                             <div>
//                                 <p>검색</p>
//                                 <p>Search</p>
//                             </div>
//                             <div>
//                                 <p>관리</p>
//                                 <p>Admin</p>
//                             </div>
//                             <p>© 2023 장지훈(github.com/jihunz), 이태성(github.com/treesap1127). 해당 사이트의 모든 요소에 대한 저작권은 이 둘에게 있으며 무단 도용과 상업적 사용을 절대 금지합니다.</p>
//                         </div>
//                     </footer>
//                 </div>
//             </div>
//         </div>
//     );
// }
//
// export default SearchPage;
