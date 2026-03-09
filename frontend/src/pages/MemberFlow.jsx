import { useState, useEffect } from 'react';
import { Link, NavLink, Outlet, useParams } from 'react-router-dom';
import { getRequestsByMember } from '../api/coachingRequests';

export default function MemberFlow() {
  const { memberId } = useParams();
  const [requests, setRequests] = useState([]);

  const load = () => getRequestsByMember(memberId).then(setRequests).catch(() => setRequests([]));

  useEffect(() => {
    load();
  }, [memberId]);

  return (
    <div className="page">
      <header className="header">
        <Link to="/">← 홈</Link>
        <span>회원 (ID: {memberId})</span>
      </header>

      <nav className="tabs">
        <NavLink to={`/member/${memberId}`} end className={({ isActive }) => isActive ? 'active' : ''}>요청 만들기</NavLink>
        <NavLink to={`/member/${memberId}/requests`} className={({ isActive }) => isActive ? 'active' : ''}>내 요청 목록</NavLink>
      </nav>

      <Outlet context={{ memberId, requests, refreshRequests: load }} />
    </div>
  );
}
