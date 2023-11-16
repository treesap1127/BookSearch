import React, {useState} from 'react';
import styles from '../../common/searchPage.css';
import Footer from "../../common/footer/Footer";
import Navigation from "../../common/navigation/Navigation";

function SearchPage() {
    const [searchResults, setSearchResults] = useState([]);

    const handleSearch = (query) => {
        // 검색 결과를 가져오는 로직을 구현하고 setSearchResults로 결과를 설정합니다.
        // 예시: 검색 API 호출 등
    };

    return (
        <div className="index">
            <Footer/>
            {/*<Navigation/>*/}
        </div>
    );
}

export default SearchPage;
