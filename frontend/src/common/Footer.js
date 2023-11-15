import React, {useState} from 'react';

function Footer() {
    return (
        <div className="container">
            <div className="text-wrapper">검색</div>
            <div className="text-wrapper-2">관리</div>
            <div className="text-wrapper-3">Search</div>
            <div className="blog">admin</div>
            <div className="overlap-group">
                <p className="element-brand-inc">
                    © 2022 장지훈(github.com/jihunz), 이태성(github.com/treesap1127). 해당 사이트의 모든 요소에 대한 저작권은
                    이 둘에게 있으며 무단 도용과 상업적 사용을 절대 금지합니다.
                </p>
            </div>
            <div className="textbox">
                <div className="text-wrapper-5">Input your email</div>
                <img
                    className="frame"
                    alt="Frame"
                    src="https://cdn.animaapp.com/projects/6554b898d013fc74e5940117/releases/6554b8c9411b2c1ade9b7f0b/img/frame.svg"
                />
            </div>
            <div className="text-wrapper-6">Subscribe to our newsletter</div>
            <p className="p">For product announcements and exclusive insights</p>
            <div className="group">
                <img
                    className="image"
                    alt="Image"
                    src="https://cdn.animaapp.com/projects/6554b898d013fc74e5940117/releases/6554b8c9411b2c1ade9b7f0b/img/image-116.svg"
                />
                <div className="text-wrapper-7">Quili-</div>
            </div>
            <button className="button">
                <div className="text-wrapper-8">Subscribe</div>
            </button>
        </div>

    );
}

export default Footer