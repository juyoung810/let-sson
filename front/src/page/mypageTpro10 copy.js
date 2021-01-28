import React from "react"
import { Link } from "react-router-dom";
import SubmitT from "../component/feature/card/submitT";
import HeadButtons from "../component/layout/header";
import SidebarMyPt from "../component/layout/sidebarMyPt";

class MypageTp extends React.Component{
    render(){
        return(
            <div>
                <HeadButtons/>
                <SidebarMyPt/>
                선생님
                <SubmitT/>
                <Link to = "/mypaget/profile">
                    <button onClick={() => alert('저장이 완료되었습니다.')}>저장하기</button>
                </Link>
                    <button name = "refresh">전부 지우기</button>
            </div>
        )
    }
}


export default MypageTp;