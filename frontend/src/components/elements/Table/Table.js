import React from "react";
import styled from "styled-components";

import { c_brown, c_dark_yellow } from "../../../utils/colors";

const Root = styled.table`
  width: 100%;
  margin-top: 2vh;
`;

const TableRow = styled.tr`
  text-align: center;
`;

const TableHead = styled.thead``;
const TableBody = styled.tbody``;

const TableData = styled.td`
  height: ${(props) => (props.tdHeight ? props.tdHeight : "60px")};
  padding: 10px;
  text-align: center;
  vertical-align: middle;
`;

const TableButton = styled.button`
  background-color: ${(props) => props.buttonColor};
  border: transparent;
  border-radius: 15px;
  height: 30px;
  cursor: pointer;
  width: 100px;
`;

const Table = ({
  buttonColor,
  buttonLabel,
  data,
  headers,
  labels,
  last,
  onClick,
  tdHeight,
}) => {
  const buttonStyleProps = {
    buttonColor: buttonColor,
  };
  const tdStyleProps = {
    tdHeight: tdHeight,
  };

  return (
    <Root>
      <TableHead>
        <TableRow>
          {headers.map((header, index) => (
            <th key={index}>{header}</th>
          ))}
        </TableRow>
      </TableHead>
      <TableBody>
        {data.map((item, itemIndex) => (
          <TableRow key={itemIndex}>
            {labels.map((label, labelIndex) => (
              <TableData {...tdStyleProps} key={labelIndex}>
                {item[label]}
              </TableData>
            ))}
            {item.name && buttonLabel ? (
              <TableData {...tdStyleProps}>
                <TableButton
                  {...buttonStyleProps}
                  onClick={() => onClick({ itemIndex })}
                >
                  {buttonLabel}
                </TableButton>
              </TableData>
            ) : null}
          </TableRow>
        ))}
      </TableBody>
    </Root>
  );
};

export default Table;
