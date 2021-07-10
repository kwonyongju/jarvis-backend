import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";

import { c_dim_gray, c_dark_gray } from "../../../utils/colors";

const Root = styled.li`
  margin-right: ${(props) => !props.last && "4rem"};

  a:hover {
    color: ${c_dark_gray};
    cursor: default;
  }
`;

const Dropdown = styled.div`
  position: relative;
  display: inline-block;

  :hover {
    div {
      display: block;
    }
  }
`;

const DropdownContent = styled.div`
  display: none;
  position: absolute;

  a {
    display: block;
    text-decoration: none;
    color: ${c_dim_gray};
    padding-top: 15px;
    width: 200px;

    :hover {
      color: ${c_dark_gray};
    }
  }
`;

const NavItem = ({ last, linkTo, name, subItems }) => {
  const RootStyle = {
    last: last,
  };

  return (
    <Dropdown>
      <Root {...RootStyle}>
        <Link to={linkTo}>{name}</Link>
      </Root>
      <DropdownContent>
        {subItems?.map((item, index) => (
          <Link key={index} to={item.label}>
            {item.name}
          </Link>
        ))}
      </DropdownContent>
    </Dropdown>
  );
};

export default NavItem;
