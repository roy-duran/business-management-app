/* -------------------------------------------------------------------------- */
/*                             Import Dependencies                            */
/* -------------------------------------------------------------------------- */
import { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import styled from "styled-components";

import AnnouncementCard from "../components/AnnouncementCard";
import CreateAnnouncement from "../components/Modals/CreateAnnouncement";
import NavBar from "../components/NavBar";
import { getCompanyAnnouncements } from "../utils/requests";
import { getAdmin, getCompany, getUser } from "../reducers/rootReducer";

const AnnouncementsStyle = styled.div`
  & div.announcements {
    align-items: center;
    display: flex;
    flex-direction: column;
    gap: 3rem;
  }
  & div.add-announcement {
    display: flex;
    align-items: end;
    flex-direction: column;
    margin: 0 15%;
    padding-bottom: 1rem;
    border-bottom: 1px solid #deb992;
    margin-bottom: 2rem;
  }
`;

const Announcements = () => {
  /* ---------------------------------- State --------------------------------- */
  // get companyId from store
  const { id: companyId } = useSelector(getCompany);
  // get user info to verify who is posting from store
  const { username } = useSelector(getUser);
  const isAdmin = useSelector(getAdmin);

  // Holds the all the announcements made to the company
  const [posts, setPosts] = useState([]);

  // Holds the user details/info
  // eslint-disable-next-line

  /* ---------------------------- helper funcitions ------------------------------ */

  const getPosts = async () => {
    // call to server to get company's posts
    setPosts(
      (await getCompanyAnnouncements(companyId)).sort(
        (a, b) =>
          new Date(b.timePosted).getTime() - new Date(a.timePosted).getTime()
      )
    );
  };

  // useEffect hook to load all posts from datase
  useEffect(() => {
    getPosts();
    // eslint-disable-next-line
  }, [posts]);

  return (
    <>
      <NavBar />
      <AnnouncementsStyle>
        <h1>Announcements</h1>
        {isAdmin ? (
          <div className="add-announcement">
            <CreateAnnouncement
              username={username}
              companyId={companyId}
              update={getPosts}
            />
          </div>
        ) : (
          ""
        )}
        <div className="announcements">
          {posts?.map(({ author, title, timePosted, message }, idx) => (
            <AnnouncementCard
              key={idx}
              firstName={author.profile.firstName}
              lastName={author.profile.lastName}
              title={title}
              postDate={timePosted}
              message={message}
            />
          ))}
        </div>
      </AnnouncementsStyle>
    </>
  );
};

export default Announcements;
