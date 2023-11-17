import React, {useState} from 'react';
import css from './navigation.css'

function Navigation() {
    return (
        <header className="n-con">
            <div className="n-wr">
                <div className="logo">
                    <img
                        alt="Image"
                        src="https://cdn.animaapp.com/projects/6554b898d013fc74e5940117/releases/6554b8c9411b2c1ade9b7f0b/img/image-120.svg"
                    />
                </div>
                <div className="search-wr">
                    <img
                        className="search-icon"
                        alt="Frame"
                        src="https://cdn.animaapp.com/projects/6554b898d013fc74e5940117/releases/6554b8c9411b2c1ade9b7f0b/img/frame-4.svg"
                    />
                    <input className="search-input" placeholder={"Search"}/>
                </div>
                <div id="setting">
                    <img
                        id="setting-logo"
                        alt="Vector"
                        src="https://cdn.animaapp.com/projects/6554b898d013fc74e5940117/releases/6554b8c9411b2c1ade9b7f0b/img/vector.svg"
                    />
                </div>
            </div>
        </header>
    );
}

export default Navigation